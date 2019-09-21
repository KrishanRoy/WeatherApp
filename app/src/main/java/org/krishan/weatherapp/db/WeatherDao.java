package org.krishan.weatherapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.krishan.weatherapp.model.ForecastModel;

public interface WeatherDao {
    @Query("SELECT * FROM ForecastModel")
    LiveData<ForecastModel> getForecastModel();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeatherInfo(ForecastModel forecastModels);
}
