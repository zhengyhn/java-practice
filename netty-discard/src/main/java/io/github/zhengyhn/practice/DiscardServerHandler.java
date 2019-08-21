package io.github.zhengyhn.practice;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
//        // Discard the received data silently.
////        ByteBuf in = (ByteBuf)msg;
////        try {
////            while (in.isReadable()) {
////                System.out.println((char)in.readByte());
////                System.out.flush();
////            }
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////        } finally {
////            in.release();
////        }
//        ctx.writeAndFlush(msg);
//    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ChannelFuture f = ctx.writeAndFlush(new UnixTime()); // (3)
        f.addListener(ChannelFutureListener.CLOSE); // (4)
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
