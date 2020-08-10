package com.cxl.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NtClient {
    private String host;
    private int port;

    public NtClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void start() throws InterruptedException {
        EventLoopGroup group=new NioEventLoopGroup();
        try {
               Bootstrap b=new Bootstrap();
               b.group(group)
                       .channel(NioSocketChannel.class)
                       .option(ChannelOption.TCP_NODELAY,true)
                       .handler(new ChannelInitializer<SocketChannel>() {
                           @Override
                           protected void initChannel(SocketChannel ch) {
                               ch.pipeline().addLast(new StringDecoder());
                               ch.pipeline().addLast(new StringEncoder());
                               ch.pipeline().addLast(new CliHandler());
                           }
                       });
               Channel channel=b.connect(host,port).sync().channel();
               BufferedReader input=new BufferedReader(new InputStreamReader(System.in));

            while (true) {
//                System.out.println("测试"+input.readLine());
                String string = XXX.convertStringToHex(input.readLine());
                channel.writeAndFlush(string+ "\n");
            }
            } catch (IOException e) {
                e.printStackTrace();
                group.shutdownGracefully();
            }finally {
                group.shutdownGracefully().sync();
            }
        }


    public static void main(String[] args) throws InterruptedException {
        new NtClient("121.199.21.197",9000).start();
//        new NtClient("127.0.0.1",9000).start();
    }
}
