package com.voxcrafterlp.monitoring.server.netty;

import com.voxcrafterlp.monitoring.server.netty.packets.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 20.12.2020
 * Time: 15:36
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
        if(packet instanceof PacketInLogin) {

        }
        if(packet instanceof PacketInUpdate) {
            System.out.println("Update");
            //TODO send data to webserver
        }
        if(packet instanceof PacketExit)
            this.channel.close();
    }
}
