package com.voxcrafterlp.monitoring.worker;

import com.voxcrafterlp.monitoring.worker.config.ConfigData;
import com.voxcrafterlp.monitoring.worker.config.ConfigLoader;
import com.voxcrafterlp.monitoring.worker.database.Database;
import com.voxcrafterlp.monitoring.worker.database.DatabaseAdapter;
import com.voxcrafterlp.monitoring.worker.enums.RowType;
import com.voxcrafterlp.monitoring.worker.log.LogLevel;
import com.voxcrafterlp.monitoring.worker.log.Logger;
import com.voxcrafterlp.monitoring.worker.netty.Client;
import com.voxcrafterlp.monitoring.worker.objects.Row;
import com.voxcrafterlp.monitoring.worker.threads.ConsoleThread;
import com.voxcrafterlp.monitoring.worker.threads.MeasurementThread;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.sql.SQLException;

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
    @Setter
    private ConfigData configData;

    private Database database;
    private DatabaseAdapter databaseAdapter;

    private ConsoleThread consoleThread;
    private MeasurementThread measurementThread;

    private Client client;

    public Application() {
        instance = this;
        try {
            this.start();
        } catch (IOException | InterruptedException | SQLException e) {
            new Logger().log(LogLevel.CRITICAL, "An error occurred while starting up");
            e.printStackTrace();
        }
    }

    public void start() throws IOException, InterruptedException, SQLException {
        Logger logger = new Logger();

        logger.log(LogLevel.INFORMATION, "Starting up...");
        Thread.sleep(750);

        //================================================//

        this.configLoader = new ConfigLoader();
        this.configData = this.configLoader.getConfigData();

        //================================================//

        logger.log(LogLevel.INFORMATION, "Connecting to database..");
        this.database = new Database();
        Thread.sleep(750);
        this.database.connect();
        this.databaseAdapter = new DatabaseAdapter();
        this.createTables();

        //================================================//

        this.consoleThread = new ConsoleThread();
        this.consoleThread.start();

        this.measurementThread = new MeasurementThread(this.configData.getUpdateInterval());
        this.measurementThread.start();

        //================================================//

        this.client = new Client();

        //================================================//
    }

    public void shutdown(String reason) throws InterruptedException {
        new Logger().log(LogLevel.INFORMATION, "Shutting down.. (" + reason + ")");

        new Logger().log(LogLevel.INFORMATION, "Disconnecting from the database..");
        this.getDatabase().disconnect();

        this.measurementThread.stop();

        Thread.sleep(850);
        new Logger().log(LogLevel.INFORMATION, "Worker stopped");
        System.exit(0);
    }

    private void createTables() {
        this.databaseAdapter.createTable("measurements",
                new Row("worker", RowType.VARCHAR),
                new Row("timestamp", RowType.DOUBLE),
                new Row("cpu_temp", RowType.DOUBLE),
                new Row("cpu_utilization", RowType.INTEGER),
                new Row("ram_utilization", RowType.INTEGER),
                new Row("swap_utilization", RowType.INTEGER));

        this.databaseAdapter.createTable("workers",
                new Row("worker", RowType.VARCHAR),
                new Row("alias", RowType.VARCHAR),
                new Row("hardware", RowType.TEXT));
    }

    public static Application getInstance() { return instance; }
}
