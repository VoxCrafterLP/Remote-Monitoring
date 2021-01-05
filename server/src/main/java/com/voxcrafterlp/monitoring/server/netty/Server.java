package com.voxcrafterlp.monitoring.server.netty;

import com.voxcrafterlp.monitoring.server.Application;
import com.voxcrafterlp.monitoring.server.log.LogLevel;
import com.voxcrafterlp.monitoring.server.log.Logger;
import com.voxcrafterlp.monitoring.server.netty.handler.PacketDecoder;
import com.voxcrafterlp.monitoring.server.netty.handler.PacketEncoder;
import com.voxcrafterlp.monitoring.server.netty.packets.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 20.12.2020
 * Time: 11:26
 * Project: Remote-Monitoring
 */

@Getter
public class Server {

    private final boolean epoll;
    private final List<Class<? extends Packet>> OUT_PACKETS = Arrays.asList(PacketOutChangeSendingState.class, PacketOutLogin.class);
    private final List<Class<? extends Packet>> IN_PACKETS = Arrays.asList(PacketInUpdate.class, PacketExit.class, PacketInLogin.class);
    private final HashMap<String, Channel> registeredWorker = new HashMap<>();

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
                            new Logger().log(LogLevel.INFORMATION, "Channel connected");
                            channel.pipeline().addLast(new PacketDecoder()).addLast(new PacketEncoder()).addLast(new NetworkHandler());
                        }
                    }).bind(Application.getInstance().getConfigData().getPort()).sync().channel().closeFuture().syncUninterruptibly().channel();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
