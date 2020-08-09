package com.cxl.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class NioWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private NioWebSocketHandler nioWebSocketHandler;
    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast(new HttpServerCodec());
        channel.pipeline().addLast(new HttpObjectAggregator(65536));
        channel.pipeline().addLast(new ChunkedWriteHandler());
        channel.pipeline().addLast(nioWebSocketHandler);
        System.out.println(">>>>>>"+nioWebSocketHandler);
    }
}
