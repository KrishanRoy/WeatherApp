package org.krishan.weatherapp.model;

import java.util.List;

public class Daily {
    private String icon;
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
