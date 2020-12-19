package com.voxcrafterlp.monitoring.objects;

import com.voxcrafterlp.monitoring.enums.RowType;

/**
 * This file was created by VoxCrafter_LP!
 * Date: 30.11.2019
 * Time: 19:18
 * For Project: Remote-Monitoring
 */

public class Row {

    private String name;
    private RowType type;

    public Row(String name, RowType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public RowType getType() {
        return type;
    }
}
