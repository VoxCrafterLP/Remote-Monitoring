package com.voxcrafterlp.monitoring.server.netty.packets;

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

    private String workerName;
    private String token;

    public PacketInLogin() {}

    public PacketInLogin(String workerName, String token) {
        this.workerName = workerName;
        this.token = token;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        int workerNameLength = byteBuf.readInt();
        int tokenLength = byteBuf.readInt();

        this.workerName = byteBuf.readCharSequence(workerNameLength, StandardCharsets.UTF_8).toString();
        this.token = byteBuf.readCharSequence(tokenLength, StandardCharsets.UTF_8).toString();
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        int workerNameLength = workerName.length();
        int tokenLength = token.length();

        byteBuf.writeInt(workerNameLength);
        byteBuf.writeInt(tokenLength);
        byteBuf.writeCharSequence(workerName, StandardCharsets.UTF_8);
        byteBuf.writeCharSequence(token, StandardCharsets.UTF_8);
    }
}
