package org.krishan.weatherapp.ui.rv.daily;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.krishan.weatherapp.R;
import org.krishan.weatherapp.model.DailyData;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private List<DailyData> modelList;

    public WeatherAdapter(List<DailyData> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_weather_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.onBind(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setData(List<DailyData> data) {
        this.modelList = data;
        notifyDataSetChanged();
    }
}
