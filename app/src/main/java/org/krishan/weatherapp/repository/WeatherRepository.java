package org.krishan.weatherapp.repository;

import org.krishan.weatherapp.model.ForecastModel;

import io.reactivex.Single;

public interface WeatherRepository {
    Single<ForecastModel> getForecastRemote(String key,
                                            double latitude,
                                            double longitude);
}
