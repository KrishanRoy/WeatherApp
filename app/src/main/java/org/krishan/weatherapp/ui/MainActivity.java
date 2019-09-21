package org.krishan.weatherapp.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import org.krishan.weatherapp.R;
import org.krishan.weatherapp.model.ForecastModel;
import org.krishan.weatherapp.ui.rv.daily.WeatherAdapter;
import org.krishan.weatherapp.ui.rv.hourly.HourlyWeatherAdapter;
import org.krishan.weatherapp.utils.DrawableResources;
import org.krishan.weatherapp.utils.StringConverterImpl;
import org.krishan.weatherapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MainActivity";
    public static final String KEY = "25cda0f29f4c03a1d58fea6d916e5970";
    private WeatherViewModel viewModel;
    private TextView addressTextView, temperatureTextView, summaryTextView, maxMinTextView, currentCityTextView;
    private ImageView currentTempIcon;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView weatherRecyclerView, hourlyRecyclerView;
    private WeatherAdapter dailyAdapter;
    private HourlyWeatherAdapter hourlyAdapter;
    private DrawableResources drawableResources = new DrawableResources();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        refreshLayout.setOnRefreshListener(this);
        if (checkIfPermissionNotGranted()) {
            askForPermission();
        } else {
            getLastLocation();
        }
        setUpDailyRecyclerView();
        setUpHourlyRecyclerView();
    }

    public void getLastLocation() {
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(this);
        locationClient
                .getLastLocation()
                .addOnSuccessListener(
                        location -> {
                            // GPS location can be null if GPS is switched off
                            if (location != null) {
                                Log.d(TAG, "onSuccess: " + location.getLatitude());
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                if (isNetworkConnected(getApplicationContext())) {
                                    viewModel.getForecastRemote(KEY, latLng.latitude, latLng.longitude).observe(this, this::renderViews);
                                } else {
                                    viewModel.getForecastLocal().observe(this, this::renderViews);
                                }

                            }
                        }
                ).addOnFailureListener(e -> {
            Log.d("getLastLocation", "cannot get last GPS location");
            e.printStackTrace();
        });
    }

    private void renderViews(ForecastModel forecastModel) {
        Log.d(TAG, "getLastLocation: " + forecastModel.getTimezone());
        progressBar.setVisibility(View.GONE);
        String temp = StringConverterImpl.formatDoubleToStringDigit(forecastModel.getCurrently().getTemperature());
        String summaryAndDate = forecastModel.getCurrently().getSummary() + "\n" +
                StringConverterImpl.convertTimeStampToDay(forecastModel.getCurrently().getTime()) + " " +
                StringConverterImpl.convertTimeStampToDate(forecastModel.getCurrently().getTime()) + "\n" +
                StringConverterImpl.convertTimeStampToTime(forecastModel.getCurrently().getTime());
        String tempMaxMin = StringConverterImpl.formatDoubleToStringDigit(forecastModel.getDaily().getData().get(0).getTemperatureHigh()) + "\n" +
                StringConverterImpl.formatDoubleToStringDigit(forecastModel.getDaily().getData().get(0).getTemperatureLow());
        temperatureTextView.setText(temp);
        summaryTextView.setText(summaryAndDate);
        currentCityTextView.setText(StringConverterImpl.formatTimeZoneToCity(forecastModel.getTimezone()));
        maxMinTextView.setText(tempMaxMin);
        Picasso.get().load(drawableResources.getIcon(forecastModel.getCurrently().getIcon())).into(currentTempIcon);
        dailyAdapter.setData(forecastModel.getDaily().getData());
        hourlyAdapter.setData(forecastModel.getHourly().getData());
        Log.d(TAG, "getLocationAndCallNetworkDaily: " + forecastModel.getDaily().getData().get(0).getTemperatureHigh());
    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void setUpHourlyRecyclerView() {
        hourlyAdapter = new HourlyWeatherAdapter(new ArrayList<>());
        LinearLayoutManager hourlyLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        hourlyRecyclerView.setAdapter(hourlyAdapter);
        hourlyRecyclerView.setLayoutManager(hourlyLayoutManager);
    }

    private void setUpDailyRecyclerView() {
        dailyAdapter = new WeatherAdapter(new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        weatherRecyclerView.setAdapter(dailyAdapter);
        weatherRecyclerView.setLayoutManager(layoutManager);
    }


    private boolean checkIfPermissionNotGranted() {
        return ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    private void askForPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);
    }

    private void findViewByIds() {
        addressTextView = findViewById(R.id.current_address_textView);
        temperatureTextView = findViewById(R.id.current_temp_textView);
        summaryTextView = findViewById(R.id.current_summary_textView);
        maxMinTextView = findViewById(R.id.current_max_min_textView);
        currentCityTextView = findViewById(R.id.current_City_textView);
        currentTempIcon = findViewById(R.id.current_temp_icon_imageView);
        progressBar = findViewById(R.id.main_activity_progress_bar);
        refreshLayout = findViewById(R.id.swipe_refresh);
        weatherRecyclerView = findViewById(R.id.daily_weather_recycler_view);
        hourlyRecyclerView = findViewById(R.id.hourly_temp_recycler_view);
    }

    @Override
    public void onRefresh() {
        getLastLocation();
        refreshLayout.setRefreshing(false);
    }
}
