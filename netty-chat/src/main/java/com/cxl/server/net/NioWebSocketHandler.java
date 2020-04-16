package com.cxl.server.net;

import com.cxl.entry.Params;
import com.cxl.entry.User;
import com.cxl.server.util.ChannelGroupUtil;
import com.cxl.server.util.ChatGroup;
import com.cxl.server.util.UserOption;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

@Component
@ChannelHandler.Sharable
public class NioWebSocketHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NioWebSocketHandler.class);
    private WebSocketServerHandshaker handshake;

    private static String address;
    @Value("${webSocket.address}")
    public  void setAddress(String address) {
        this.address = address;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        LOGGER.debug("收到消息："+o);
        if (o instanceof FullHttpRequest) {
            handleHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        } else if (o instanceof WebSocketFrame) {
            handleWebSocket(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("客户端加入连接:"+ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("客户端断开连接："+ctx.channel());
        ChannelGroupUtil.removeChannel(ctx.channel());
        UserOption.removeUser(ctx.channel());
    }


    private void handleHttpRequest(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) {
        if (request.decoderResult().isSuccess() || ("websocket".equalsIgnoreCase(request.headers().get("Upgrade")))) {
            String uri = request.uri();
            System.out.println(".........."+address);
            WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(address, null, false);
            handshake = factory.newHandshaker(request);
            if (null == handshake) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channelHandlerContext.channel());
            } else {
                User user = new User(uri.substring(uri.indexOf("?") + 1), channelHandlerContext.channel().id().toString());
                UserOption.addUser(user);
                handshake.handshake(channelHandlerContext.channel(), request);
                ChannelGroupUtil.addChannel(channelHandlerContext.channel());
                //保存用户名和channelId
                Params params = new Params(Params.SYS, user.toString());

                TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(params.toString());
                System.out.println("qqqcxxxxaacac"+textWebSocketFrame);
                channelHandlerContext.writeAndFlush(textWebSocketFrame);
            }
        } else {
            sendHttpResponse(channelHandlerContext, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_GATEWAY));
        }

    }

    private void handleWebSocket(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
        if (frame instanceof CloseWebSocketFrame) {
            handshake.close(channelHandlerContext.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }

        String request = ((TextWebSocketFrame) frame).text();
        System.out.println(request);
        webSocketDispatcher(channelHandlerContext, (TextWebSocketFrame) frame);
    }

    private void webSocketDispatcher(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame o) {
        String request = o.text();
        ObjectMapper objectMapper = new ObjectMapper();
        Params params = new Params();
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(request);
        try {
            JsonNode jsonNode = objectMapper.readTree(request);
            String type = jsonNode.path("payLoad").path("type").asText();
            if (Params.PVP.equals(type)) {
                String channelId = jsonNode.path("target").asText();
                Channel channel = ChannelGroupUtil.findChannel(channelId);
                if (null != channel) {
                    channel.writeAndFlush(textWebSocketFrame);
                }
            } else if (Params.PVG.equals(type)) {
                String groupChatName = jsonNode.path("target").asText();
                String channelId = jsonNode.path("source").asText();
                ChannelGroup channels = ChatGroup.getChatGroup(groupChatName);
                if (null != channels) {
                    Channel channel = ChannelGroupUtil.findChannel(channelId);
                    //先删自己
                    channels.remove(channel);

                    channels.writeAndFlush(textWebSocketFrame);
                    //重新加进来
                    channels.add(channel);
                }
            }
        } catch (IOException e) {
            params.setType(Params.ERROR);
            params.setData("消息发送失败！[ " + request + " ]");
            channelHandlerContext.writeAndFlush(new TextWebSocketFrame(params.toString()));
        }

    }

    private void sendHttpResponse(ChannelHandlerContext channelHandlerContext, FullHttpRequest request, DefaultFullHttpResponse defaultFullHttpResponse) {
        if (200 != defaultFullHttpResponse.status().code()) {
            ByteBuf buf = Unpooled.copiedBuffer(defaultFullHttpResponse.status().toString(), CharsetUtil.UTF_8);
            buf.release();
        }
        ChannelFuture future = channelHandlerContext.writeAndFlush(defaultFullHttpResponse);
        if (!isKeepAlive(request) || 200 != defaultFullHttpResponse.status().code()) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            channel.closeFuture();
        }
    }
}
