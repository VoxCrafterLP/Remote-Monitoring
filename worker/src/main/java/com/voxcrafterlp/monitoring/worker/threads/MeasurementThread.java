package com.voxcrafterlp.monitoring.worker.threads;

import com.voxcrafterlp.monitoring.worker.Application;
import com.voxcrafterlp.monitoring.worker.log.LogLevel;
import com.voxcrafterlp.monitoring.worker.log.Logger;
import com.voxcrafterlp.monitoring.worker.objects.Insert;
import com.voxcrafterlp.monitoring.worker.objects.Key;
import com.voxcrafterlp.monitoring.worker.utils.HardwareInfoReader;
import lombok.SneakyThrows;

import java.net.InetAddress;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 30.11.2020
 * Time: 08:37
 * Project: Remote-Monitoring
 */

public class MeasurementThread extends Thread {

    private final long updateInterval;
    private HardwareInfoReader hardwareInfoReader;

    public MeasurementThread(long updateInterval) {
        this.updateInterval = updateInterval * 1000; //Convert to seconds
    }

    @SneakyThrows
    @Override
    public void run() {
        this.hardwareInfoReader = new HardwareInfoReader();
        this.updateHardwareInformation();

        while (true) {
            Thread.sleep(this.updateInterval);
            int cpuUtilization = hardwareInfoReader.getCPUUtilization();
            double cpuTemperature = hardwareInfoReader.getCPUTemperature();
            int memoryUsed = hardwareInfoReader.getRamUtilization();
            int swapUsed = hardwareInfoReader.getSwapUtilization();

            this.newMeasurement(cpuUtilization, cpuTemperature, memoryUsed, swapUsed);
        }
    }

    /**
     * Saves a new measurement into the database
     */
    private void newMeasurement(int cpuUtilization, double cpuTemperature, int memoryUsed, int swapUsed) {
        new Logger().log(LogLevel.INFORMATION, "New measurement");
        Application.getInstance().getDatabaseAdapter().insertIntoTable("measurements",
                new Insert("worker", Application.getInstance().getConfigData().getWorkerName()),
                new Insert("timestamp", System.currentTimeMillis()),
                new Insert("cpu_temp", cpuTemperature),
                new Insert("cpu_utilization", cpuUtilization),
                new Insert("ram_utilization", memoryUsed),
                new Insert("swap_utilization", swapUsed));
        new Logger().log(LogLevel.INFORMATION, "Measurement saved");
    }

    /**
     * Gets and updates/puts hardware information in the database
     */
    private void updateHardwareInformation() {
        if(!Application.getInstance().getDatabaseAdapter().containsEntry("workers", new Key("worker", Application.getInstance().getConfigData().getWorkerName()))) {
            new Logger().log(LogLevel.INFORMATION, "Inserting worker information in database");

            Application.getInstance().getDatabaseAdapter().insertIntoTable("workers",
                    new Insert("worker", Application.getInstance().getConfigData().getWorkerName()),
                    new Insert("alias", ""), new Insert("hardware", this.hardwareInfoReader.getHardwareInfo().toString()));
        } else {
            new Logger().log(LogLevel.INFORMATION, "Updating worker information in database");
            Application.getInstance().getDatabaseAdapter().updateValue("workers", "hardware", this.hardwareInfoReader.getHardwareInfo().toString(),
                    new Key("worker", Application.getInstance().getConfigData().getWorkerName()));
        }
    }

}
