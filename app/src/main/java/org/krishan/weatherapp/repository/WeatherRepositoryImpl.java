package org.krishan.weatherapp.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.krishan.weatherapp.db.WeatherDatabase;
import org.krishan.weatherapp.model.ForecastModel;
import org.krishan.weatherapp.network.RetrofitSingleton;
import org.krishan.weatherapp.network.WeatherService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepositoryImpl implements WeatherRepository {
    private WeatherService weatherService = RetrofitSingleton.getInstance().create(WeatherService.class);
    private WeatherDatabase weatherDatabase;

    /**
     * @param application needed to provide context for the Room db
     */
    public WeatherRepositoryImpl(Application application) {
        weatherDatabase = WeatherDatabase.getDatabase(application.getApplicationContext());
    }

    /**
     * this method subscribes on a background thread and returns
     * Single ForecastModel via Retrofit Call
     */
    @Override
    public Single<ForecastModel> getForecastRemote(String key, double latitude, double longitude) {
        return weatherService
                .getRemoteForecast(key, latitude, longitude)
                .subscribeOn(Schedulers.io());
    }

    /**
     * this method first deletes all the preexisting info and then updates
     * the Room db with new remote forecast
     */

    @SuppressLint("StaticFieldLeak")
    @Override
    public void insertRemoteForecastToRoomDB(ForecastModel forecastModel) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                weatherDatabase.weatherDao().insertWeatherInfo(forecastModel);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<ForecastModel> getForecastLocal() {
        return weatherDatabase.weatherDao().getForecastModel();
    }
}
