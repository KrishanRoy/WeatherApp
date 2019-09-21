package org.krishan.weatherapp.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ForecastModel")
public class ForecastModel {
    @NonNull
    @PrimaryKey
    private String timezone;
    @Embedded(prefix = "currently_")
    private Currently currently;
    @Embedded(prefix = "daily_")
    private Daily daily;
    @Embedded(prefix = "hourly_")
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
