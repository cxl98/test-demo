package com.cxl.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class NtServer {
    private  int port;

    public NtServer(int port) {
        this.port = port;
    }

    public void start(){
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup work=new NioEventLoopGroup();
        try {
             ServerBootstrap bootstrap=new ServerBootstrap();

             bootstrap.group(boss,work)
                      .channel(NioServerSocketChannel.class)
//                      .option(ChannelOption.SO_BACKLOG,1024)
                      .option(ChannelOption.SO_KEEPALIVE,true)
//                      .option(ChannelOption.TCP_NODELAY,true)
                      .childHandler(new ChannelInitializer<SocketChannel>() {
                          @Override
                          protected void initChannel(SocketChannel channel) throws Exception {
                              channel.pipeline().addLast(new HttpServerCodec());
                              channel.pipeline().addLast(new HttpObjectAggregator(65535));
//                              channel.pipeline().addLast(new ChunkedWriteHandler());
                              channel.pipeline().addLast(new SerHander());
                          }
                      });


            ChannelFuture future=bootstrap.bind(port).sync();
            System.out.println("服务器启动");
            future.channel().closeFuture().sync();
            System.out.println("服务器关闭");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NtServer(9000).start();
    }
}
