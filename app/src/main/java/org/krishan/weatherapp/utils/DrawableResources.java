package org.krishan.weatherapp.utils;

import org.krishan.weatherapp.R;

import java.util.HashMap;
import java.util.Map;

public class DrawableResources {
    private Map<String, Integer> iconMap = new HashMap<>();

    public DrawableResources() {
        putResourcesToMap();
    }

    private void putResourcesToMap() {
        iconMap.put("clear-day", R.drawable.clear_day);
        iconMap.put("rain", R.drawable.rainy_day);
        iconMap.put("partly-cloudy-day", R.drawable.partly_cloudy_day);
        iconMap.put("clear-night", R.drawable.clear_night);
        iconMap.put("partly-cloudy-night", R.drawable.partly_cloudy_night);
        iconMap.put("generic", R.drawable.generic_image);
    }

    public Integer getIcon(String key) {
        if (iconMap.containsKey(key)) {
            return iconMap.get(key);
        } else {
            return iconMap.get("generic");
        }
    }

}
