package org.krishan.weatherapp.rv;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.krishan.weatherapp.model.DailyData;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    private TextView highTempTextView, lowTempTextView;
    private ImageView iconImageView;
    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public void onBind(DailyData daily) {
    }
}
