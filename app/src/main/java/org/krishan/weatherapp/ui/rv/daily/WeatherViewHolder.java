package org.krishan.weatherapp.ui.rv.daily;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.krishan.weatherapp.R;
import org.krishan.weatherapp.model.DailyData;
import org.krishan.weatherapp.utils.DrawableResources;
import org.krishan.weatherapp.utils.StringConverterImpl;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    private final TextView dayDateTextView;
    private final TextView summaryTextView;
    private final TextView highLowTempTextView;
    private final ImageView iconImageView;
    private final DrawableResources drawableResources = new DrawableResources();
    private StringConverterImpl stringConverter = new StringConverterImpl();

    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);
        dayDateTextView = itemView.findViewById(R.id.day_date_textView);
        summaryTextView = itemView.findViewById(R.id.summary_textView);
        highLowTempTextView = itemView.findViewById(R.id.high_low_temp_textView);
        iconImageView = itemView.findViewById(R.id.temp_icon_imageView);
    }

    public void onBind(DailyData daily) {
        String tempHighAndLow = StringConverterImpl.formatDoubleToStringDigit(daily.getTemperatureHigh()) + "\n" +
                StringConverterImpl.formatDoubleToStringDigit(daily.getTemperatureLow());
        String dayAndDate = StringConverterImpl.convertTimeStampToDay(daily.getTime()) + " "
                + StringConverterImpl.convertTimeStampToDate(daily.getTime());
        highLowTempTextView.setText(tempHighAndLow);
        dayDateTextView.setText(dayAndDate);
        summaryTextView.setText(daily.getSummary());
        Picasso.get().load(drawableResources.getIcon(daily.getIcon())).into(iconImageView);
    }
}
