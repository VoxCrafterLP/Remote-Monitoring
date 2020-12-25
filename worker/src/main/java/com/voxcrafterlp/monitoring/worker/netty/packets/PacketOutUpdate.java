package com.voxcrafterlp.monitoring.worker.netty.packets;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import java.io.IOException;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 25.12.2020
 * Time: 19:32
 * Project: Remote-Monitoring
 */

@Getter
public class PacketOutUpdate implements Packet {

    private int cpuUtilization;
    private double cpuTemperature;
    private int memoryUsed;
    private int swapUsed;

    public PacketOutUpdate() {}

    public PacketOutUpdate(int cpuUtilization, double cpuTemperature, int memoryUsed, int swapUsed) {
        this.cpuUtilization = cpuUtilization;
        this.cpuTemperature = cpuTemperature;
        this.memoryUsed = memoryUsed;
        this.swapUsed = swapUsed;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        this.cpuUtilization = byteBuf.readInt();
        this.cpuTemperature = byteBuf.readDouble();
        this.memoryUsed = byteBuf.readInt();
        this.swapUsed = byteBuf.readInt();
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        byteBuf.writeInt(this.cpuUtilization);
        byteBuf.writeDouble(this.cpuTemperature);
        byteBuf.writeInt(this.memoryUsed);
        byteBuf.writeInt(this.swapUsed);
    }
}
