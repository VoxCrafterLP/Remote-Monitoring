package com.voxcrafterlp.monitoring.server.config;

import lombok.Getter;
import org.json.JSONObject;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 29.11.2020
 * Time: 15:10
 * Project: Remote-Monitoring
 */

@Getter
public class ConfigData {

    // DATABASE
    private final String databaseHost;
    private final String databaseUsername;
    private final String databasePassword;
    private final int databasePort;
    private final String databaseName;

    // GENERAL
    private final int port;

    /**
     * Loads the config entries from the JSONObject
     * @param jsonObject JSONObject read from the config file
     */
    public ConfigData(JSONObject jsonObject) {
        this.databaseHost = jsonObject.getJSONObject("database").getString("databaseHost");
        this.databaseUsername = jsonObject.getJSONObject("database").getString("databaseUsername");
        this.databasePassword = jsonObject.getJSONObject("database").getString("databasePassword");
        this.databasePort = jsonObject.getJSONObject("database").getInt("databasePort");
        this.databaseName = jsonObject.getJSONObject("database").getString("databaseName");

        this.port = jsonObject.getJSONObject("general").getInt("port");
    }

}
