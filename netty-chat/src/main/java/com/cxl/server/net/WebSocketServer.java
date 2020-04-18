package com.cxl.server.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WebSocketServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    @Autowired
    private NioWebSocketChannelInitializer nioWebSocketChannelInitializer;
//    private static int port;

    //    @Value("${webSocket.port}")
//    public void setPort(int port) {
//        this.port = port;
//    }
    private Thread thread;


    public void run(int port) {
        LOGGER.info("init websocket 服务器");
        thread = new Thread(() -> {
            EventLoopGroup bossgroup = new NioEventLoopGroup();
            EventLoopGroup workgroup = new NioEventLoopGroup();
            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(bossgroup, workgroup).channel(NioServerSocketChannel.class).childHandler(nioWebSocketChannelInitializer);
                ChannelFuture future = bootstrap.bind(port).sync();
                LOGGER.info("webSocket服务器启动成功");
                LOGGER.info("port    " + port);
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                LOGGER.error(">>>>>>" + e);
            } finally {
                bossgroup.shutdownGracefully();
                workgroup.shutdownGracefully();
                LOGGER.info("webSocket服务器已关闭");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void stop() {
        if (null != thread && thread.isAlive()){
            thread.interrupt();
        }
        LOGGER.info(">>>>>>>>>>> WebServer destroy success.");
    }
}
