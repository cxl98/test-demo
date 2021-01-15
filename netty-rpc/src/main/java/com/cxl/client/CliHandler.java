package com.cxl.client;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;

import java.nio.charset.StandardCharsets;

public class CliHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) throws Exception {
        byte[] bytes = ByteBufUtil.getBytes(fullHttpResponse.content());
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }
}
