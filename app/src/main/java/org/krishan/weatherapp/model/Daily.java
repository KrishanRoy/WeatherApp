package org.krishan.weatherapp.model;

import androidx.room.TypeConverters;

import org.krishan.weatherapp.utils.DailyDataTypeConverters;

import java.util.List;

public class Daily {
    private String icon;
    @TypeConverters(DailyDataTypeConverters.class)
    private List<DailyData> data;

    public Daily(String icon, List<DailyData> data) {
        this.icon = icon;
        this.data = data;
    }

    public String getIcon() {
        return icon;
    }

    public List<DailyData> getData() {
        return data;
    }
}
