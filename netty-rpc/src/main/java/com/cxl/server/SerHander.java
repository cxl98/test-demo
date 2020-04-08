package com.cxl.server;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class SerHander extends SimpleChannelInboundHandler<String> {
    public static ChannelGroup channels=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel inConfig=ctx.channel();//获取客户端的通道
        for (Channel channel: channels){
            if (channel!=inConfig){
                channel.writeAndFlush("欢迎:"+inConfig.remoteAddress()+"进入聊天室"+"\n");
            }

        }
        channels.add(inConfig);//加入队列
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel outConfig=ctx.channel();//获取客服端的通道
        for (Channel channel: channels){
            if (channel!=outConfig){
                channel.writeAndFlush("再见"+outConfig.remoteAddress()+"离开聊天室!"+"\n");
            }

        }
        channels.remove(outConfig);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel inConfig=ctx.channel();
        System.out.println(inConfig.remoteAddress()+":"+"在线"+"\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel inConfig=ctx.channel();
        System.out.println(inConfig.remoteAddress()+":"+"离线"+"\n");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel inConfig=ctx.channel();
        System.out.println(inConfig.remoteAddress()+" 通讯异常");
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel inConfig=ctx.channel();
        System.out.println("SerHandler的handlerRead0111"+msg+"\n");
        for (Channel channel: channels){
            System.out.println("SerHandler的handlerRead0222"+msg+"\n");
            if (channel!=inConfig){
                channel.writeAndFlush("用户:"+inConfig.remoteAddress()+"说"+msg+"\n");
            }else{
                channel.writeAndFlush("我说:"+msg+"\n");
            }
        }
    }

}
