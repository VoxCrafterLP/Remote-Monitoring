package com.voxcrafterlp.monitoring;

import com.voxcrafterlp.monitoring.config.ConfigData;
import com.voxcrafterlp.monitoring.config.ConfigLoader;
import com.voxcrafterlp.monitoring.database.Database;
import com.voxcrafterlp.monitoring.database.DatabaseAdapter;
import com.voxcrafterlp.monitoring.enums.RowType;
import com.voxcrafterlp.monitoring.log.LogLevel;
import com.voxcrafterlp.monitoring.log.Logger;
import com.voxcrafterlp.monitoring.objects.Row;
import lombok.Getter;

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
    private ConfigData configData;

    private Database database;
    private DatabaseAdapter databaseAdapter;

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
    }

    private void createTables() {
        this.databaseAdapter.createTable("measurements",
                new Row("worker", RowType.VARCHAR),
                new Row("timestamp", RowType.DOUBLE),
                new Row("cpu_temp", RowType.INTEGER),
                new Row("cpu_utilization", RowType.INTEGER),
                new Row("ram_utilization", RowType.INTEGER),
                new Row("swap_utilization", RowType.INTEGER));
    }

    public static Application getInstance() { return instance; }
}
