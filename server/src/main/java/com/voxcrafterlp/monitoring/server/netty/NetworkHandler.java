package com.voxcrafterlp.monitoring.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 20.12.2020
 * Time: 15:36
 * Project: Remote-Monitoring
 */

public class NetworkHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channel = ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

    }
}
