package org.krishan.weatherapp.model;


public class ForecastModel {
    private String timezone;
    private Currently currently;
    private Daily daily;


    public ForecastModel(String timezone, Currently currently, Daily daily) {
        this.timezone = timezone;
        this.currently = currently;
        this.daily = daily;
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
}
