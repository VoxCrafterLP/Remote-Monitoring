package com.voxcrafterlp.monitoring.worker.netty.handler;

import com.voxcrafterlp.monitoring.worker.Application;
import com.voxcrafterlp.monitoring.worker.netty.packets.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 25.12.2020
 * Time: 18:58
 * Project: Remote-Monitoring
 */

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> output) throws Exception {
        int id = byteBuf.readInt();
        Class<? extends Packet> packetClass = Application.getInstance().getClient().getIN_PACKETS().get(id);

        if(packetClass == null)
            throw new NullPointerException("Couldn't find packet by id " + id);

        Packet packet = packetClass.newInstance();
        packet.read(byteBuf);
        output.add(packet);
    }
}
