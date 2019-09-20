package org.krishan.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.krishan.weatherapp.network.RetrofitSingleton;
import org.krishan.weatherapp.network.WeatherService;
import org.krishan.weatherapp.viewmodel.WeatherViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "MainActivity";
    private WeatherViewModel viewModel;
    private TextView addressTextView, temperatureTextView, summaryTextView;
    private ImageView currentTempIcon;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
}
