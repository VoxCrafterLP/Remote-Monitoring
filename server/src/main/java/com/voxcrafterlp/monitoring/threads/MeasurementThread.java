package com.voxcrafterlp.monitoring.threads;


import com.voxcrafterlp.monitoring.utils.HardwareInfoReader;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 30.11.2020
 * Time: 08:37
 * Project: Remote-Monitoring
 */

public class MeasurementThread extends Thread {

    private final double updateInterval;
    private HardwareInfoReader hardwareInfoReader;

    public MeasurementThread(double updateInterval) {
        this.updateInterval = updateInterval;
    }

    @Override
    public void run() {
        this.hardwareInfoReader = new HardwareInfoReader();
    }

}
