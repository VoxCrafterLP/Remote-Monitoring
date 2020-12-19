package com.voxcrafterlp.monitoring.server.objects;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 30.11.2019
 * Time: 20:12
 * For Project: Remote-Monitoring
 */

public class Insert {

    private String row;
    private String value;

    public Insert(String row, String value) {
        this.row = row;
        this.value = value;
    }
    public Insert(String row, int value) {
        this.row = row;
        this.value = String.valueOf(value);
    }

    public Insert(String row, double value) {
        this.row = row;
        this.value = String.valueOf(value);
    }

    public Insert(String row, long value) {
        this.row = row;
        this.value = String.valueOf(value);
    }


    public String getRow() {
        return row;
    }

    public String getValue() {
        return value;
    }
}
