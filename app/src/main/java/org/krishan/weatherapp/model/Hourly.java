package org.krishan.weatherapp.model;

import java.util.List;

public class Hourly {
    private List<HourlyData> data;

    public List<HourlyData> getData() {
        return data;
    }

    public Hourly(List<HourlyData> data) {
        this.data = data;
    }
}
