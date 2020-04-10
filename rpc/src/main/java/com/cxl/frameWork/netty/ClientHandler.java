package com.cxl.frameWork.netty;

import com.cxl.frameWork.HelloService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

public class ClientHandler extends SimpleChannelInboundHandler {
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println(o);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RpcRequest r = (RpcRequest) requests(HelloService.class);
        ctx.writeAndFlush(r);
    }

    private Object requests(Class inface) {
        final RpcRequest rpcRequest = new RpcRequest();
        Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{inface}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                rpcRequest.setRequestId(UUID.randomUUID().toString());
                rpcRequest.setMethodName(method.getName());
                Class<?>[] parameterTypes = method.getParameterTypes();
                rpcRequest.setClassName(parameterTypes);
                rpcRequest.setParameters(args);
                return rpcRequest;
            }
        });
        return rpcRequest;
    }
}
