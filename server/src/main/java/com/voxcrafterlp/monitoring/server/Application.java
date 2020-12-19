package com.voxcrafterlp.monitoring.server;

import com.voxcrafterlp.monitoring.server.config.ConfigData;
import com.voxcrafterlp.monitoring.server.config.ConfigLoader;
import com.voxcrafterlp.monitoring.server.database.Database;
import com.voxcrafterlp.monitoring.server.database.DatabaseAdapter;
import com.voxcrafterlp.monitoring.server.enums.RowType;
import com.voxcrafterlp.monitoring.server.log.LogLevel;
import com.voxcrafterlp.monitoring.server.log.Logger;
import com.voxcrafterlp.monitoring.server.objects.Row;
import com.voxcrafterlp.monitoring.server.threads.ConsoleThread;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.sql.SQLException;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 19.12.2020
 * Time: 23:04
 * Project: Remote-Monitoring
 */

@Getter
public class Application {

    private static Application instance;

    @Setter
    private ConfigData configData;
    private ConfigLoader configLoader;

    private Database database;
    private DatabaseAdapter databaseAdapter;

    private ConsoleThread consoleThread;

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

        //================================================//
    }

    public void shutdown(String reason) throws InterruptedException {
        new Logger().log(LogLevel.INFORMATION, "Shutting down.. (" + reason + ")");

        new Logger().log(LogLevel.INFORMATION, "Disconnecting from the database..");
        this.getDatabase().disconnect();

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
