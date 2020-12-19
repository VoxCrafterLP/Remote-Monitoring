package com.voxcrafterlp.monitoring.worker.log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 29.11.2020
 * Time: 12:59
 * Project: Remote-Monitoring
 */

public class Logger {

    public void log(LogLevel logLevel, String content) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String time = simpleDateFormat.format(new Date());

        System.out.println(new StringBuffer().append("[").append(time).append("] [").append(logLevel.getDisplay()).append("] ").append(content).toString());
    }

}
