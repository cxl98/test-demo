//package com.cxl.codec;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.ByteToMessageDecoder;
//
//import java.nio.charset.Charset;
//import java.util.List;
//
//public class NettyDecoder extends ByteToMessageDecoder {
//    private Class<?> genericClass;
//    private Serializer serializer;
//
//    public NettyDecoder(Class<?> genericClass, final Serializer serializer) {
//        this.genericClass = genericClass;
//        this.serializer = serializer;
//    }
//
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        System.out.println("xxxx"+in.toString(Charset.defaultCharset()));
//        if (in.readableBytes()<4){
//            return ;
//        }
//        in.markReaderIndex();
//        int dataLength=in.readInt();
//        if (dataLength<0){
//            ctx.close();
//        }
//        if (in.readableBytes()<dataLength) {
//            in.markReaderIndex();
//            return ;
//        }
//        byte [] data=new byte[dataLength];
//
//        System.out.println(dataLength);
//        in.readBytes(data);
//        System.out.println(data);
//
//        Object obj=serializer.deserializer(data,genericClass);
//        System.out.println(obj);
//        out.add(obj);
//    }
//}
