package com.cxl.client;

import com.cxl.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Client {

    private EventLoopGroup workgroup;

    public void init(String address) {
        String[] hostAndport = address.split(":");
        String host = hostAndport[0];
        int port = Integer.parseInt(hostAndport[1]);
        start(host, port);
    }

    private void start(String host, int port) {
        workgroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture future = null;
        try {
            bootstrap.group(workgroup).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new LineBasedFrameDecoder(2048)).addLast(new StringEncoder()).addLast(new StringDecoder()).addLast(new ClientHandler());
                        }
                    });
            future = bootstrap.connect(host, port).sync();
            InputStreamReader is = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(is);
                while (true) {
                    future.channel().writeAndFlush(br.readLine() + "\n");
                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workgroup.shutdownGracefully();
            bootstrap.clone();
        }
    }

    public static void main(String[] args) {
        Client client=new Client();
        client.init("127.0.0.1:8888");
    }
}
