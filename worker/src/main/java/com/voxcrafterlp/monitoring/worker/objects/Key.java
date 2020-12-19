package com.voxcrafterlp.monitoring.worker.objects;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 30.11.2019
 * Time: 19:55
 * For Project: Remote-Monitoring
 */

public class Key {

    private String row;
    private String keyWord;

    public Key(String row, String keyWord) {
        this.row = row;
        this.keyWord = keyWord;
    }

    public String getRow() {
        return row;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
