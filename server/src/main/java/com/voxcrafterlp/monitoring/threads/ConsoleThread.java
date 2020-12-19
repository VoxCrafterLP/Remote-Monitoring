package com.voxcrafterlp.monitoring.threads;

import com.voxcrafterlp.monitoring.Application;
import com.voxcrafterlp.monitoring.log.LogLevel;
import com.voxcrafterlp.monitoring.log.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 30.11.2020
 * Time: 08:37
 * Project: Remote-Monitoring
 */

public class ConsoleThread extends Thread {

    @Override
    public void run() {
        new Logger().log(LogLevel.INFORMATION, "Started up! Type 'help' or '?' for help");

        String line = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while ((line = reader.readLine()) != null) {
                if(line.equalsIgnoreCase("stop") || line.equalsIgnoreCase("shutdown")) {
                    try {
                        Application.getInstance().shutdown("Manual shutdown");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reader.close();
                } else
                if(line.equalsIgnoreCase("help") || line.equalsIgnoreCase("?")) {
                    new Logger().log(LogLevel.INFORMATION, "==============================");
                    new Logger().log(LogLevel.INFORMATION, "Available commands:");
                    new Logger().log(LogLevel.INFORMATION, "- help / ? : Shows this list");
                    new Logger().log(LogLevel.INFORMATION, "- stop / shutdown : Stops the worker");
                    new Logger().log(LogLevel.INFORMATION, "- reload : Reloads the configuration of the worker");
                    new Logger().log(LogLevel.INFORMATION, "==============================");
                } else
                if(line.equalsIgnoreCase("reload")) {
                    try {
                        Application.getInstance().getConfigLoader().reloadConfig();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else
                    new Logger().log(LogLevel.ERROR, "Unknown command! Please type 'help' or '?' for help!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
