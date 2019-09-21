package org.krishan.weatherapp.model;

import androidx.room.TypeConverters;

import org.krishan.weatherapp.utils.HourlyDataTypeConverter;

import java.util.List;

public class Hourly {
    @TypeConverters(HourlyDataTypeConverter.class)
    private final List<HourlyData> data;

    public List<HourlyData> getData() {
        return data;
    }

    public Hourly(List<HourlyData> data) {
        this.data = data;
    }
}
