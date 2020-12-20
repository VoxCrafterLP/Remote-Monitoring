package com.voxcrafterlp.monitoring.server.netty;

import com.voxcrafterlp.monitoring.server.Application;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 20.12.2020
 * Time: 11:26
 * Project: Remote-Monitoring-Worker
 */

public class Server {

    public final boolean epoll;

    public Server() throws InterruptedException {
        this.epoll = Epoll.isAvailable();

        EventLoopGroup eventLoopGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            new ServerBootstrap()
                    .group(eventLoopGroup)
                    .channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {

                        @Override
                        protected void initChannel(Channel channel) {
                            channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8)).
                                    addLast(new StringEncoder(StandardCharsets.UTF_8))
                                    .addLast(new NetworkHandler());
                        }
                    }).bind(Application.getInstance().getConfigData().getPort()).sync().channel().closeFuture().syncUninterruptibly();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
