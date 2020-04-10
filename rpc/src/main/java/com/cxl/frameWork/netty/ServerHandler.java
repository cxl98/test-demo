package com.cxl.frameWork.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler {
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object s) throws Exception {
        RpcRequest rpcRequest= (RpcRequest) s;
        if (null!=rpcRequest){
            System.out.println("cxl  "+rpcRequest.toString());
            channelHandlerContext.writeAndFlush(resp(rpcRequest.getRequestId(),rpcRequest));
        }
    }

    private Object resp(String id,RpcRequest rpcRequest) {
        RpcResponse rpcResponse=new RpcResponse();
        rpcResponse.setRequestId(id);
        rpcResponse.setResult(rpcRequest);
        return rpcResponse;
    }
}
