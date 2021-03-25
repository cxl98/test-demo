package com.cxl.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class NtClient {
    private String host;
    private int port;

    public NtClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void start() throws InterruptedException, URISyntaxException {
        EventLoopGroup group=new NioEventLoopGroup();
        try {
               Bootstrap b=new Bootstrap();
               b.group(group)
                       .channel(NioSocketChannel.class)
//                       .option(ChannelOption.TCP_NODELAY,true)
                       .handler(new ChannelInitializer<SocketChannel>() {
                           @Override
                           protected void initChannel(SocketChannel ch) {
                               ch.pipeline().addLast(new CliHandler());
                           }
                       });
               Channel channel=b.connect(host,port).sync().channel();
               BufferedReader input=new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String s = input.readLine();
//                channel.writeAndFlush(s+ "\r\n");
                channel.writeAndFlush(s).sync();
            }
            } catch (IOException e) {
                e.printStackTrace();
                group.shutdownGracefully();
            }finally {
                group.shutdownGracefully().sync();
            }
        }


    public static void main(String[] args) throws InterruptedException, URISyntaxException {
//        new NtClient("121.199.21.197",8700).start();
        new NtClient("127.0.0.1",8700).start();
//        new NtClient("39.97.209.17",9003).start();
    }
}
