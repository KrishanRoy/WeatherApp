package org.krishan.weatherapp.ui.rv.hourly;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.krishan.weatherapp.R;
import org.krishan.weatherapp.model.HourlyData;
import org.krishan.weatherapp.utils.DrawableResources;
import org.krishan.weatherapp.utils.StringConverterImpl;

public class HourlyViewHolder extends RecyclerView.ViewHolder {
    private final TextView hourTextView;
    private final TextView tempTextView;
    private final ImageView iconImageView;
    private StringConverterImpl stringConverter = new StringConverterImpl();
    private final DrawableResources drawableResources = new DrawableResources();

    public HourlyViewHolder(@NonNull View itemView) {
        super(itemView);
        hourTextView = itemView.findViewById(R.id.hourly_itemView_hour_textView);
        tempTextView = itemView.findViewById(R.id.hourly_itemView_temp_textView);
        iconImageView = itemView.findViewById(R.id.hourly_itemView_hour_imageView);
    }

    public void onBind(HourlyData hourlyData) {
        String hour = StringConverterImpl.convertTimeStampToOneDigitTime(hourlyData.getTime());
        String temp = StringConverterImpl.formatDoubleToStringDigit(hourlyData.getTemperature());
        hourTextView.setText(hour);
        tempTextView.setText(temp);
        Picasso.get().load(drawableResources.getIcon(hourlyData.getIcon())).into(iconImageView);
    }
}
