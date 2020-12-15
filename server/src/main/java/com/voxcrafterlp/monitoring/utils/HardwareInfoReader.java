package com.voxcrafterlp.monitoring.utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 12.12.2020
 * Time: 10:21
 * Project: Remote-Monitoring
 */

public class HardwareInfoReader {

    /**
     * Gets the current ram utilization
     * @return Returns ram usage in megabytes
     */
    public long getRamUtilization() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        GlobalMemory globalMemory = hardwareAbstractionLayer.getMemory();
        return (globalMemory.getTotal() - globalMemory.getAvailable()) / 1000000;
    }

    /**
     * Gets how much ram is installed in the system
     * @return Returns total ram capacity in megabytes
     */
    public long getTotalRamAvailable() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        GlobalMemory globalMemory = hardwareAbstractionLayer.getMemory();
        return globalMemory.getTotal() / 1000000;
    }

    /*public double getCPUUtilization() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
        return (centralProcessor.getSystemCpuLoadBetweenTicks());
    }*/





}
