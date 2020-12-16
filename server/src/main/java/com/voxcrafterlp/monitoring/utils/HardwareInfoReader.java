package com.voxcrafterlp.monitoring.utils;

import org.json.JSONObject;
import oshi.SystemInfo;
import oshi.hardware.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 12.12.2020
 * Time: 10:21
 * Project: Remote-Monitoring
 */

public class HardwareInfoReader {

    private long[] oldTicks;
    private final CentralProcessor centralProcessor;

    public HardwareInfoReader() {
        CentralProcessor centralProcessor = new SystemInfo().getHardware().getProcessor();
        oldTicks = new long[CentralProcessor.TickType.values().length];
        this.centralProcessor = centralProcessor;

        this.init();
    }

    public void init() {
        new Thread(() -> {
            try {
                Thread.sleep(100);
                this.cpuData(this.centralProcessor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Gets the current ram utilization
     * @return Ram usage in megabytes
     */
    public int getRamUtilization() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        GlobalMemory globalMemory = hardwareAbstractionLayer.getMemory();
        return (int) ((globalMemory.getTotal() - globalMemory.getAvailable()) / 1000000);
    }

    /**
     * Gets the current ram utilization
     * @return Ram usage in percent
     */
    public int getRamUtilizationInPercent() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        GlobalMemory globalMemory = hardwareAbstractionLayer.getMemory();

        double usedMemory = ((globalMemory.getTotal() - globalMemory.getAvailable()) / 1000000.0);
        double availableMemory = (globalMemory.getTotal() / 1000000.0);

        return (int) ((usedMemory / availableMemory) * 100);
    }

    /**
     * Gets the current swap utilization
     * @return Swap usage in megabytes
     */
    public int getSwapUtilization() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        VirtualMemory virtualMemory = hardwareAbstractionLayer.getMemory().getVirtualMemory();
        return (int) (virtualMemory.getSwapUsed() / 1000000);
    }

    /**
     * Gets the current swap utilization
     * @return Swap usage in percent
     */
    public int getSwapUtilizationInPercent() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        VirtualMemory virtualMemory = hardwareAbstractionLayer.getMemory().getVirtualMemory();

        double usedSwap = (virtualMemory.getSwapUsed() / 1000000.0);
        double availableSwap = (virtualMemory.getSwapTotal() / 1000000.0);

        return (int) ((usedSwap / availableSwap) * 100);
    }

    /**
     * Gets how much ram is installed in the system
     * @return Total ram capacity in megabytes
     */
    public long getTotalRamAvailable() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        GlobalMemory globalMemory = hardwareAbstractionLayer.getMemory();
        return globalMemory.getTotal() / 1000000;
    }

    /**
     * Gets the current cpu utilization
     * @return Current cpu usage in percent
     */
    public int getCPUUtilization() {
        return (int) (cpuData(this.centralProcessor) * 100);
    }

    /**
     * Gets the cpu temperature
     * @return Current cpu temp in degrees
     */
    public double getCPUTemperature() {
        return new SystemInfo().getHardware().getSensors().getCpuTemperature();
    }

    /**
     * @return Hardware installed in a JSONObject
     */
    public JSONObject getHardwareInfo() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();

        JSONObject cpu = new JSONObject();
        cpu.put("model", this.centralProcessor.getProcessorIdentifier().getName());
        cpu.put("packages_installed", this.centralProcessor.getPhysicalPackageCount());
        cpu.put("cores", this.centralProcessor.getPhysicalProcessorCount());
        cpu.put("vcores", this.centralProcessor.getLogicalProcessorCount());

        JSONObject memory = new JSONObject();
        memory.put("memory_installed", hardwareAbstractionLayer.getMemory().getTotal());
        memory.put("swap_installed", hardwareAbstractionLayer.getMemory().getVirtualMemory().getSwapTotal());

        JSONObject system = new JSONObject();
        try {
            system.put("os_name", System.getProperty("os.name"));
            system.put("current_user", System.getProperty("user.name"));
            system.put("ip_address", InetAddress.getLocalHost().getHostAddress());
            system.put("hostname", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cpu", cpu);
        jsonObject.put("memory", memory);
        jsonObject.put("system", system);
        return jsonObject;
    }


    private double cpuData(CentralProcessor proc) {
        double data = proc.getSystemCpuLoadBetweenTicks(oldTicks);
        this.oldTicks = proc.getSystemCpuLoadTicks();
        return data;
    }

}
