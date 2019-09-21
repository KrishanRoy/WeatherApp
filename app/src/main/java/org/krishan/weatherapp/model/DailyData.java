package org.krishan.weatherapp.model;


public class DailyData {
    private final int time;
    private final double temperatureHigh;
    private final double temperatureLow;
    private final String icon;
    private final String summary;

    public DailyData(int time, double temperatureHigh, double temperatureLow, String icon, String summary) {
        this.time = time;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.icon = icon;
        this.summary = summary;
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

    public String getSummary() {
        return summary;
    }
}
