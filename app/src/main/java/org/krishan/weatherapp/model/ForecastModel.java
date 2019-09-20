package org.krishan.weatherapp.model;


public class ForecastModel {
    private String timezone;
    private Currently currently;
    private Daily daily;
    private Hourly hourly;


    public ForecastModel(String timezone, Currently currently, Daily daily, Hourly hourly) {
        this.timezone = timezone;
        this.currently = currently;
        this.daily = daily;
        this.hourly = hourly;
    }

    public String getTimezone() {
        return timezone;
    }

    public Currently getCurrently() {
        return currently;
    }

    public Daily getDaily() {
        return daily;
    }

    public Hourly getHourly() {
        return hourly;
    }
}
