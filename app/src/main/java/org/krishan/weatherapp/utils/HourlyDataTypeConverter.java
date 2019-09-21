package org.krishan.weatherapp.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.krishan.weatherapp.model.HourlyData;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class HourlyDataTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<HourlyData> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<HourlyData>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<HourlyData> someObjects) {
        return gson.toJson(someObjects);
    }
}
