package com.cxl.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        Channel channel = ctx.channel();
//        channels.writeAndFlush("服务器--->>> " + channel.remoteAddress() + " channel_id: " + channel.id() + " 加入\n");
////        channels.flush();
//        channels.add(channel);
//    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("服务器--->>> " + channel.remoteAddress() + " 离开\n");
//        remove()这个方法是将存活的踢出去，所以下面的方法不需要执行
//        channels.remove(channel);
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        final Channel channel = channelHandlerContext.channel();
        channels.forEach(itemChannel -> {
                if (channel!=itemChannel) {
                    itemChannel.writeAndFlush("[" + channel.remoteAddress() + "]" + s + "\n");
                }else{
                    itemChannel.writeAndFlush(s+"\n");
                }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("服务器--->>>" + channel.remoteAddress() + "上线了\n");
        channels.writeAndFlush("服务器--->>> " + channel.remoteAddress() + " channel_id: " + channel.id() + " 加入\n");
        channels.add(channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("服务器--->>>" + channel.remoteAddress() + "掉线线了\n");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("服务器" + channel.remoteAddress() + "异常");
        ctx.close();
    }
}
