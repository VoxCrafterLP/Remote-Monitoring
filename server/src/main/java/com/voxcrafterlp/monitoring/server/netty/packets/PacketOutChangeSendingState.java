package com.voxcrafterlp.monitoring.server.netty.packets;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

import java.io.IOException;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 25.12.2020
 * Time: 19:39
 * Project: Remote-Monitoring
 */

@Getter
public class PacketOutChangeSendingState implements Packet {

    private boolean sending;

    public PacketOutChangeSendingState() {}

    public PacketOutChangeSendingState(boolean sending) {
        this.sending = sending;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        this.sending = byteBuf.readBoolean();
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        byteBuf.writeBoolean(this.sending);
    }
}
