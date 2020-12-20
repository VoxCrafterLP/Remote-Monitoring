package com.voxcrafterlp.monitoring.worker.netty;

import com.voxcrafterlp.monitoring.worker.Application;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 20.12.2020
 * Time: 15:04
 * Project: Remote-Monitoring-Worker
 */

public class Client {

    public final boolean epoll;
    private Channel channel;

    public Client() throws InterruptedException {
        this.epoll = Epoll.isAvailable();

        EventLoopGroup eventLoopGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            this.channel = new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(epoll ? EpollSocketChannel.class : NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {

                        @Override
                        protected void initChannel(Channel channel) {
                            channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8)).addLast(new StringEncoder(StandardCharsets.UTF_8));
                        }
                    }).connect(Application.getInstance().getConfigData().getServerHost(), Application.getInstance().getConfigData().getServerPort())
                    .sync().channel();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public void disconnect() throws InterruptedException {
        channel.closeFuture().sync();
    }

}
