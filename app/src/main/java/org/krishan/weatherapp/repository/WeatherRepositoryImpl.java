package org.krishan.weatherapp.repository;

import android.content.Context;

import androidx.room.Room;

import org.krishan.weatherapp.db.WeatherDatabase;
import org.krishan.weatherapp.model.ForecastModel;
import org.krishan.weatherapp.network.RetrofitSingleton;
import org.krishan.weatherapp.network.WeatherService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepositoryImpl implements WeatherRepository {
    private WeatherService weatherService = RetrofitSingleton.getInstance().create(WeatherService.class);
    private WeatherDatabase weatherDatabase;

    @Override
    public Single<ForecastModel> getForecastRemote(String key, double latitude, double longitude) {
        return weatherService
                .getRemoteForecast(key, latitude, longitude)
                .subscribeOn(Schedulers.io());
    }
}
