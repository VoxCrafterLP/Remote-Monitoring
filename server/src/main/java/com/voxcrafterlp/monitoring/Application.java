package com.voxcrafterlp.monitoring;

import lombok.Getter;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 29.11.2020
 * Time: 12:51
 * Project: Remote-Monitoring
 */

@Getter
public class Application {

    private static Application instance;

    public Application() {
        instance = this;
        this.start();
    }

    public void start() {

    }

}
