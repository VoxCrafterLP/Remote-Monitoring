package com.voxcrafterlp.monitoring;

import com.voxcrafterlp.monitoring.config.ConfigData;
import com.voxcrafterlp.monitoring.config.ConfigLoader;
import com.voxcrafterlp.monitoring.log.LogLevel;
import com.voxcrafterlp.monitoring.log.Logger;
import lombok.Getter;

import java.io.IOException;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 29.11.2020
 * Time: 12:51
 * Project: Remote-Monitoring
 */

@Getter
public class Application {

    private static Application instance;
    private ConfigLoader configLoader;
    private ConfigData configData;

    public Application() {
        instance = this;
        try {
            this.start();
        } catch (IOException | InterruptedException e) {
            new Logger().log(LogLevel.CRITICAL, "An error occurred while starting up");
            e.printStackTrace();
        }
    }

    public void start() throws IOException, InterruptedException {
        Logger logger = new Logger();

        logger.log(LogLevel.INFORMATION, "Starting up...");
        Thread.sleep(750);

        this.configLoader = new ConfigLoader();
        this.configData = this.configLoader.getConfigData();
    }

}
