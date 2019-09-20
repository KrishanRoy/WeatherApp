package org.krishan.weatherapp.repository;

import org.krishan.weatherapp.model.ForecastModel;
import org.krishan.weatherapp.network.RetrofitSingleton;
import org.krishan.weatherapp.network.WeatherService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

class WeatherRepositoryImpl implements WeatherRepository {
    private WeatherService weatherService = RetrofitSingleton.getInstance().create(WeatherService.class);


    @Override
    public Single<ForecastModel> getForecastRemote(String key, double latitude, double longitude) {
        return weatherService
                .getRemoteForecast(key, latitude, longitude)
                .subscribeOn(Schedulers.io());
    }
}
