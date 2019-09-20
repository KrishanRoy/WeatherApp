package org.krishan.weatherapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.krishan.weatherapp.network.RetrofitSingleton;
import org.krishan.weatherapp.network.WeatherService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitSingleton.getInstance().create(WeatherService.class)
                .getRemoteForecast("25cda0f29f4c03a1d58fea6d916e5970", 40.7128, -74.0060)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        forecastModel -> {
                            Log.d(TAG, "onCreate: " + forecastModel.getTimezone());
                        },
                        throwable -> {
                        }
                );
    }
}
