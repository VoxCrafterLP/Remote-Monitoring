package com.voxcrafterlp.monitoring.worker.netty;

import com.voxcrafterlp.monitoring.worker.Application;
import com.voxcrafterlp.monitoring.worker.netty.packets.Packet;
import com.voxcrafterlp.monitoring.worker.netty.packets.PacketInChangeSendingState;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 25.12.2020
 * Time: 19:27
 * Project: Remote-Monitoring
 */

public class NetworkHandler extends SimpleChannelInboundHandler<Packet> {

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channel = ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        System.out.println("kek");
        if(packet instanceof PacketInChangeSendingState) {
            System.out.println("lol empfangen");
            if(((PacketInChangeSendingState) packet).isSend())
                Application.getInstance().getLiveUpdateThread().start();
            else
                Application.getInstance().getLiveUpdateThread().stop();
        }
    }
}
