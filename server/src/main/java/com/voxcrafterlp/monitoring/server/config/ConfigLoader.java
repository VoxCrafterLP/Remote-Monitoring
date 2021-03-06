package com.voxcrafterlp.monitoring.server.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.voxcrafterlp.monitoring.server.Application;
import com.voxcrafterlp.monitoring.server.log.LogLevel;
import com.voxcrafterlp.monitoring.server.log.Logger;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 29.11.2020
 * Time: 14:49
 * Project: Remote-Monitoring
 */

@Getter
public class ConfigLoader {

    private final File configFile;
    private ConfigData configData;

    public ConfigLoader() throws IOException, InterruptedException {
        new Logger().log(LogLevel.INFORMATION, "Loading config file..");

        this.configFile = new File("server-configuration.json");
        this.loadDefaultConfig();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.configFile.getPath()));
        List<String> lines = Files.readAllLines(this.configFile.toPath());

        StringBuffer stringBuffer = new StringBuffer();
        lines.forEach(stringBuffer::append);

        if(!this.isValidJson(stringBuffer.toString())) {
            new Logger().log(LogLevel.ERROR, "Invalid config file!");
            new Logger().log(LogLevel.ERROR, "Shutting down..");
            Application.getInstance().shutdown("Config error");
            return;
        }
        new Logger().log(LogLevel.INFORMATION, "Config found! Loading configuration");
        this.configData = new ConfigData(new JSONObject(stringBuffer.toString()));
        bufferedReader.close();
    }

    private boolean isValidJson(String string) {
        try {
            new JSONObject(string);
            return true;
        } catch (JSONException exception) {
            return false;
        }
    }

    /**
     * Reloads the config file
     */
    public void reloadConfig() throws IOException, InterruptedException {
        new Logger().log(LogLevel.INFORMATION, "Reloading config file..");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.configFile.getPath()));
        List<String> lines = Files.readAllLines(this.configFile.toPath());

        StringBuffer stringBuffer = new StringBuffer();
        lines.forEach(stringBuffer::append);

        if(!this.isValidJson(stringBuffer.toString())) {
            new Logger().log(LogLevel.ERROR, "Invalid config file!");
            new Logger().log(LogLevel.ERROR, "Shutting down..");
            Application.getInstance().shutdown("Config error");
            return;
        }
        this.configData = new ConfigData(new JSONObject(stringBuffer.toString()));
        bufferedReader.close();
        new Logger().log(LogLevel.INFORMATION, "Config reloaded!");
        Application.getInstance().setConfigData(this.configData);
    }

    /**
     * Loads the default config values
     */
    private void loadDefaultConfig() throws IOException {
        if(!this.configFile.exists()) {
            JSONObject databaseSettings = new JSONObject();
            databaseSettings.put("databaseHost", "localhost");
            databaseSettings.put("databaseUsername", "root");
            databaseSettings.put("databasePassword", "");
            databaseSettings.put("databasePort", "3306");
            databaseSettings.put("databaseName", "Monitoring");

            JSONObject generalSettings = new JSONObject();
            generalSettings.put("port", "2021");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("database", databaseSettings);
            jsonObject.put("general", generalSettings);


            JsonElement jsonElement = new JsonParser().parse(jsonObject.toString());
            String prettyJsonString = new GsonBuilder().setPrettyPrinting().create().toJson(jsonElement);

            this.configFile.createNewFile();
            FileWriter fileWriter = new FileWriter(this.configFile);
            fileWriter.write(prettyJsonString);
            fileWriter.close();

            new Logger().log(LogLevel.INFORMATION, "Loaded default config");
        }
    }

}
