package com.cxl.driver.net;

import com.cxl.driver.util.HexUtil;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Configuration
@Component
@ChannelHandler.Sharable
public class DriverHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端加入连接:"+ctx.channel());
        LOGGER.debug("客户端加入连接:"+ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        byte[] bytes = HexUtil.hexStringToBytes(msg);
        System.out.println(bytes.length);
        String s = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s);
    }
}
