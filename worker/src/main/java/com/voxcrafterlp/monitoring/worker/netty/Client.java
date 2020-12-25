package com.voxcrafterlp.monitoring.worker.netty;

import com.voxcrafterlp.monitoring.worker.Application;
import com.voxcrafterlp.monitoring.worker.netty.handler.PacketDecoder;
import com.voxcrafterlp.monitoring.worker.netty.handler.PacketEncoder;
import com.voxcrafterlp.monitoring.worker.netty.packets.Packet;
import com.voxcrafterlp.monitoring.worker.netty.packets.PacketExit;
import com.voxcrafterlp.monitoring.worker.netty.packets.PacketInChangeSendingState;
import com.voxcrafterlp.monitoring.worker.netty.packets.PacketOutUpdate;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 20.12.2020
 * Time: 15:04
 * Project: Remote-Monitoring
 */

@Getter
public class Client {

    public final boolean epoll;
    private Channel channel;
    private final List<Class<? extends Packet>> OUT_PACKETS = Arrays.asList(PacketOutUpdate.class, PacketExit.class);
    private final List<Class<? extends Packet>> IN_PACKETS = Arrays.asList(PacketInChangeSendingState.class);

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
                            channel.pipeline().addLast(new PacketDecoder()).addLast(new PacketEncoder()).addLast(new NetworkHandler());
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
