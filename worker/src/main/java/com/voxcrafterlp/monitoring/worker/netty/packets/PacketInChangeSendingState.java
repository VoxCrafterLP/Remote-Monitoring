package com.voxcrafterlp.monitoring.worker.netty.packets;

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
public class PacketInChangeSendingState implements Packet {

    private boolean send;

    public PacketInChangeSendingState() {}

    public PacketInChangeSendingState(boolean sending) {
        this.send = sending;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        this.send = byteBuf.readBoolean();
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        byteBuf.writeBoolean(this.send);
    }
}
