package com.voxcrafterlp.monitoring.worker.netty.packets;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 25.12.2020
 * Time: 18:55
 * Project: Remote-Monitoring
 */

public interface Packet {

    public void read(ByteBuf byteBuf) throws IOException;
    public void write(ByteBuf byteBuf) throws IOException;

}
