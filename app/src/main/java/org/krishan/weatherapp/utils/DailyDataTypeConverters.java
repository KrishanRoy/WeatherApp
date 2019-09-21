package org.krishan.weatherapp.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.krishan.weatherapp.model.DailyData;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DailyDataTypeConverters {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<DailyData> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<DailyData>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<DailyData> someObjects) {
        return gson.toJson(someObjects);
    }
}
