package org.krishan.weatherapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class SplashActivity extends AppCompatActivity {
    public static final String TAG = "SplashActivity";
    public static final String LAT_KEY = "latitude";
    public static final String LONG_KEY = "longitude";
    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (checkIfPermissionNotGranted()) {
            askForPermission();
        } else {
            //TODO: if the GPS is off, prompt dialogue to turn it on{

            //TODO: getLastLocation() --> Make Network Call
            // }
            getLastLocation();
            Button button = findViewById(R.id.splash_button);
            button.setOnClickListener(v -> sendToMain());
        }
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
        locationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    // GPS location can be null if GPS is switched off
                    if (location != null) {
                        Log.d(TAG, "onSuccess: " + location.getLatitude());
                        latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    }
                }).addOnFailureListener(e -> {
            Log.d("getLastLocation", "cannot get last GPS location");
            e.printStackTrace();
        });
    }

    private void sendToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(LAT_KEY, latLng.latitude);
        intent.putExtra(LONG_KEY, latLng.longitude);
        startActivity(intent);
    }
}
