package com.voxcrafterlp.monitoring.database;

import com.voxcrafterlp.monitoring.Application;
import com.voxcrafterlp.monitoring.log.LogLevel;
import com.voxcrafterlp.monitoring.log.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 29.11.2020
 * Time: 15:57
 * Project: Remote-Monitoring
 */

public class Database {

    private final String host;
    private final String user;
    private final String password;
    private final int port;
    private final String database;
    private Connection connection;

    public Database() {
        this.host = Application.getInstance().getConfigData().getDatabaseHost();
        this.user = Application.getInstance().getConfigData().getDatabaseUsername();
        this.password = Application.getInstance().getConfigData().getDatabasePassword();
        this.port = Application.getInstance().getConfigData().getDatabasePort();
        this.database = Application.getInstance().getConfigData().getDatabaseName();
    }

    /**
     * Connects to the database
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
            new Logger().log(LogLevel.INFORMATION, "Successfully connected to the database");
        } catch (SQLException exception) {
            new Logger().log(LogLevel.ERROR, "An error occurred while connecting to the database");
            exception.printStackTrace();
        }
    }

    /**
     * Disconnects from the database
     */
    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return Returns if the connection has been established
     */
    public boolean isConnected() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }

}
