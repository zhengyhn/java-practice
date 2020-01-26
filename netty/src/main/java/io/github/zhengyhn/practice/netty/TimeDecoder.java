package io.github.zhengyhn.practice.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class TimeDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
       out.add(new UnixTime(in.readUnsignedInt()));
    }
}
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.ByteToMessageDecoder;
//
//import java.util.List;
//
//public class TimeDecoder extends ByteToMessageDecoder { // (1)
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
//        if (in.readableBytes() < 4) {
//            return; // (3)
//        }
//        out.add(new UnixTime(in.readUnsignedInt()));
//    }
//}