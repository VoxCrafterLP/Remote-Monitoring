package com.voxcrafterlp.monitoring.objects;

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

    public String getRow() {
        return row;
    }

    public String getValue() {
        return value;
    }
}
