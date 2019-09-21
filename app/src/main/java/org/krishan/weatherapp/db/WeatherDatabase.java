package org.krishan.weatherapp.db;

import androidx.room.Database;

import org.krishan.weatherapp.model.ForecastModel;

@Database(entities = {ForecastModel.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase {
    public abstract WeatherDao weatherDao();

    private static WeatherDatabase INSTANCE;

}
