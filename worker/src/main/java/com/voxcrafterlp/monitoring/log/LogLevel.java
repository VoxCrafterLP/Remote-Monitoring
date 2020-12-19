package com.voxcrafterlp.monitoring.log;

import lombok.Getter;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 29.11.2020
 * Time: 12:55
 * Project: Remote-Monitoring
 */

@Getter
public enum LogLevel {

    INFORMATION("INFO"),
    WARNING("WARNING"),
    ERROR("EROOR"),
    CRITICAL("CRITICAL");

    private final String display;

    LogLevel(String display) {
        this.display = display;
    }

    public static String format(LogLevel logLevel) {
        return new StringBuffer().append("[").append(logLevel.getDisplay()).append("]").toString();
    }

}
