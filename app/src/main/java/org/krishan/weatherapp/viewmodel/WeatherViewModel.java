package org.krishan.weatherapp.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.krishan.weatherapp.model.ForecastModel;
import org.krishan.weatherapp.repository.WeatherRepositoryImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class WeatherViewModel extends AndroidViewModel {
    private WeatherRepositoryImpl repository;
    private CompositeDisposable disposable = new CompositeDisposable();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    public LiveData<ForecastModel> getForecastRemote(String key, double latitude, double longitude) {
        MutableLiveData<ForecastModel> mutableLiveData = new MutableLiveData<>();
        disposable.add(repository
                .getForecastRemote(key, latitude, longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        forecastModel -> {
                            mutableLiveData.setValue(forecastModel);
                        },
                        throwable -> {
                        }
                ));
        return mutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
