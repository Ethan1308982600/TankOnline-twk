package com.twk.tank.TankOnline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void ServerStart(){
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(2);

        ServerBootstrap bs =   new ServerBootstrap();
        try {
            ChannelFuture future =  bs.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pl = socketChannel.pipeline();
                            pl.addLast(new serverHandler());
                        }
                    }).bind(8888).sync();
            ServerFrame.INSTANCE.updateServerMsg("Server started, waiting for connection !");
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class serverHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*服务端在接收到某个客户端的消息以后，需要把该消息进行广播
        */
        Server.clients.writeAndFlush(msg);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Server.clients.remove(ctx.channel());
        ctx.close();
    }

}