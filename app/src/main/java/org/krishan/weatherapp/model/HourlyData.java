package org.krishan.weatherapp.model;

public class HourlyData {
    private final long time;
    private final double temperature;
    private final String icon;

    public HourlyData(long time, double temperature, String icon) {
        this.time = time;
        this.temperature = temperature;
        this.icon = icon;
    }

    public long getTime() {
        return time;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getIcon() {
        return icon;
    }
}
