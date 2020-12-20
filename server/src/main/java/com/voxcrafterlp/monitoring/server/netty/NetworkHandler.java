package com.voxcrafterlp.monitoring.server.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 20.12.2020
 * Time: 15:36
 * Project: Remote-Monitoring-Worker
 */

public class NetworkHandler extends SimpleChannelInboundHandler<String> {

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channel = ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String string) throws Exception {
        if(string.equals("close"))  {
            channel.close();
            return;
        }
        System.out.println(string);
    }
}
