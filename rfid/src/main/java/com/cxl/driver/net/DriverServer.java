package com.cxl.driver.net;

import com.cxl.driver.cconfig.Properties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Configuration
public class DriverServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverServer.class);
    private Thread thread;
    @Resource
    private DriverSocketChannelInitializer channelInitializer;
    @Resource
    private Properties properties;
    public void start(){
        thread = new Thread(() -> {
            EventLoopGroup bossgroup = new NioEventLoopGroup();
            EventLoopGroup workgroup = new NioEventLoopGroup();
            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(bossgroup, workgroup)
                        .channel(NioServerSocketChannel.class).childHandler(channelInitializer)
                        .option(ChannelOption.SO_BACKLOG,1024)
                        .option(ChannelOption.SO_KEEPALIVE,true)
                        .option(ChannelOption.TCP_NODELAY,true);
                ChannelFuture future = bootstrap.bind(properties.getPort()).sync();
                LOGGER.info("webSocket服务器启动成功");
                LOGGER.info("port    " + properties.getPort());
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                LOGGER.error(">>>>>>" + e);
            } finally {
                bossgroup.shutdownGracefully();
                workgroup.shutdownGracefully();
                LOGGER.info("webSocket服务器已关闭");
            }
        });
        thread.start();
    }

    public void stop() {
        if (null != thread && thread.isAlive()){
            thread.interrupt();
        }
        LOGGER.info(">>>>>>>>>>> WebServer destroy success.");
    }
}
