package org.krishan.weatherapp.ui.rv.hourly;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.krishan.weatherapp.R;
import org.krishan.weatherapp.model.HourlyData;

import java.util.List;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyViewHolder> {
    private List<HourlyData> hourlyDataList;

    public HourlyWeatherAdapter(List<HourlyData> hourlyDataList) {
        this.hourlyDataList = hourlyDataList;
    }

    @NonNull
    @Override
    public HourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HourlyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_weather_itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyViewHolder holder, int position) {
        holder.onBind(hourlyDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return hourlyDataList.size();
    }

    public void setData(List<HourlyData> data) {
        this.hourlyDataList = data;
        notifyDataSetChanged();
    }
}
