package org.krishan.weatherapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import org.krishan.weatherapp.model.ForecastModel;
import org.krishan.weatherapp.viewmodel.WeatherViewModel;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MainActivity";
    public static final String KEY = "25cda0f29f4c03a1d58fea6d916e5970";
    private WeatherViewModel viewModel;
    private TextView addressTextView, temperatureTextView, summaryTextView;
    private ImageView currentTempIcon;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        if (checkIfPermissionNotGranted()) {
            askForPermission();
        } else {
            //TODO: if the GPS is off, prompt dialogue to turn it on{

            //TODO: getLastLocation() --> Make Network Call
            // }
            getLastLocation();
        }
    }

    private void findViewByIds() {
        addressTextView = findViewById(R.id.current_address_textView);
        temperatureTextView = findViewById(R.id.current_temp_textView);
        summaryTextView = findViewById(R.id.current_summary_textView);
        currentTempIcon = findViewById(R.id.current_temp_icon_imageView);
        progressBar = findViewById(R.id.main_activity_progress_bar);
        refreshLayout = findViewById(R.id.swipe_refresh);
    }


    @Override
    public void onRefresh() {
        //TODO:
        // renderViews()
        refreshLayout.setRefreshing(false);
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
                                viewModel.getForecastRemote(KEY, latLng.latitude, latLng.longitude).observe(this, forecastModel -> {
                                            Log.d(TAG, "getLastLocation: " + forecastModel.getTimezone());
                                            //TODO: renderViews()
                                            progressBar.setVisibility(View.GONE);
                                            String temp = formatDoubleToStringDigit(forecastModel.getCurrently().getTemperature());
                                            String summaryAndDate = formatTimeZoneToCity(forecastModel.getTimezone()) + "\n" +
                                                    forecastModel.getCurrently().getSummary() + " "
                                                    + convertTimeStampToDayAndDate(forecastModel.getCurrently().getTime());
                                            temperatureTextView.setText(temp);
                                            summaryTextView.setText(summaryAndDate);
                                            Log.d(TAG, "getLocationAndCallNetworkDaily: " + forecastModel.getDaily().getData().get(0).getTemperatureHigh());
                                        });
                            }
                        }).addOnFailureListener(e -> {
            Log.d("getLastLocation", "cannot get last GPS location");
            e.printStackTrace();
        });
    }

    private String convertTimeStampToDayAndDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(time * 1000);
        int dayInt = cal.get(Calendar.DAY_OF_WEEK);
        String weekday = new DateFormatSymbols().getShortWeekdays()[dayInt];
        String dayAndDate = weekday + "\n" + DateFormat.format("M/dd h:mm a", cal).toString(); //should render Fri 09/17 11:26 AM
        return dayAndDate;
    }


    private String formatTimeZoneToCity(String timeZone) {
        if (timeZone != null && !timeZone.equals("")) {
            return timeZone.substring(timeZone.indexOf('/') + 1).replace("_", " ");
        } else {
            return "World";
        }
    }

    private String formatDoubleToStringDigit(double temp) {
        return "" + (int) Math.round(temp);
    }
}
//TODO: observeLocation from ViewModel

//TODO: observeRemoteForecast from ViewModel

//TODO: LocalForecast (when Room DB is implemented) from ViewModel
