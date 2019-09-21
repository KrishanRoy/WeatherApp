package org.krishan.weatherapp.model;

public class Currently {
    private final double temperature;
    private final long time;
    private final String summary;
    private final String icon;

    public Currently(double temperature, long time, String summary, String icon) {
        this.temperature = temperature;
        this.time = time;
        this.summary = summary;
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getIcon() {
        return icon;
    }

    public long getTime() {
        return time;
    }
}
