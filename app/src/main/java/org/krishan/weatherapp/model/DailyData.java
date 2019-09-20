package org.krishan.weatherapp.model;

public class DailyData {
    private int time;
    private double temperatureHigh;
    private double temperatureLow;
    private String icon;

    public DailyData(int time, double temperatureHigh, double temperatureLow, String icon) {
        this.time = time;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.icon = icon;
    }

    public int getTime() {
        return time;
    }

    public double getTemperatureHigh() {
        return temperatureHigh;
    }

    public double getTemperatureLow() {
        return temperatureLow;
    }

    public String getIcon() {
        return icon;
    }
}
