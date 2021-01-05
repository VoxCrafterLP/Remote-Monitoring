package com.voxcrafterlp.monitoring.worker.netty.handler;

import com.voxcrafterlp.monitoring.worker.Application;
import com.voxcrafterlp.monitoring.worker.netty.packets.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 25.12.2020
 * Time: 19:00
 * Project: Remote-Monitoring
 */

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf output) throws Exception {
        int id = Application.getInstance().getClient().getOUT_PACKETS().indexOf(packet.getClass());
        if(id == -1)
            throw new NullPointerException("Couldn't find id of packet " + packet.getClass().getSimpleName());

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(id);
        byteBuf.writeBytes(output);
        packet.write(byteBuf);
    }
}
