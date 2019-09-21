package org.krishan.weatherapp.utils;

import org.krishan.weatherapp.R;

import java.util.HashMap;
import java.util.Map;

public class DrawableResources {
    private final Map<String, Integer> iconMap = new HashMap<>();

    public DrawableResources() {
        putResourcesToMap();
    }

    private void putResourcesToMap() {
        iconMap.put("clear-day", R.drawable.clear_day);
        iconMap.put("rain", R.drawable.rainy_day_night);
        iconMap.put("partly-cloudy-day", R.drawable.partly_cloudy_day);
        iconMap.put("clear-night", R.drawable.clear_night);
        iconMap.put("partly-cloudy-night", R.drawable.partly_cloudy_day);
        iconMap.put("generic", R.drawable.generic);
        iconMap.put("wind-icon", R.drawable.wind);
        iconMap.put("air-pressure", R.drawable.air_pressure);
        iconMap.put("snow-fall", R.drawable.snow_fall);
    }

    public Integer getIcon(String key) {
        if (iconMap.containsKey(key)) {
            return iconMap.get(key);
        } else {
            return iconMap.get("generic");
        }
    }

}
