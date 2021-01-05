package com.voxcrafterlp.monitoring.worker.netty.packets;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 05.01.2021
 * Time: 15:03
 * Project: Remote-Monitoring
 */

@Getter
public class PacketInLogin implements Packet {

    private boolean success;

    public PacketInLogin() {}

    public PacketInLogin(boolean success) {
        this.success = success;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        this.success = byteBuf.readBoolean();
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        byteBuf.writeBoolean(this.success);
    }
}
