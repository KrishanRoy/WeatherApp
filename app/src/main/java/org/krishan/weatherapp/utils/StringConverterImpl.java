package org.krishan.weatherapp.utils;

import android.text.format.DateFormat;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class StringConverterImpl {
    private static StringConverterImpl stringConverter;
    public static final String TAG = "tag for the try catch";

    public static StringConverterImpl newInstance() {
        if (stringConverter == null) {
            stringConverter = new StringConverterImpl();
        }
        return stringConverter;
    }

    public static String convertTimeStampToDay(long time) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(time * 1000);
        int dayInt = cal.get(Calendar.DAY_OF_WEEK);
        return new DateFormatSymbols().getShortWeekdays()[dayInt];
    }


    public static String convertTimeStampToTime(long time) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(time * 1000);
        return DateFormat.format("h:mm a", cal).toString();
    }


    public static String convertTimeStampToOneDigitTime(long time) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(time * 1000);
        return DateFormat.format("h a", cal).toString();
    }


    public static String convertTimeStampToDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(time * 1000);
        return DateFormat.format("M/dd", cal).toString();
    }


    public static String formatTimeZoneToCity(String timeZone) {
        if (timeZone != null && !timeZone.equals("")) {
            return timeZone.substring(timeZone.indexOf('/') + 1).replace("_", " ");
        } else {
            return "World";
        }
    }


    public static String formatDoubleToStringDigit(double temp) {
        return "" + (int) Math.round(temp);
    }
}
