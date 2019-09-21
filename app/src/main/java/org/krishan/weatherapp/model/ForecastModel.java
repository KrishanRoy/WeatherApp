package org.krishan.weatherapp.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ForecastModel")
public class ForecastModel {
    @NonNull
    @PrimaryKey
    private final String timezone;
    @Embedded(prefix = "currently_")
    private final Currently currently;
    @Embedded(prefix = "daily_")
    private final Daily daily;
    @Embedded(prefix = "hourly_")
    private final Hourly hourly;


    public ForecastModel(@NonNull String timezone, Currently currently, Daily daily, Hourly hourly) {
        this.timezone = timezone;
        this.currently = currently;
        this.daily = daily;
        this.hourly = hourly;
    }
    @NonNull
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
