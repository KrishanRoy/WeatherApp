package org.krishan.weatherapp.utils;

import android.text.format.DateFormat;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class StringConverterImpl implements StringConverter {
    @Override
    public String convertTimeStampToDay(long time) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(time * 1000);
        int dayInt = cal.get(Calendar.DAY_OF_WEEK);
        return new DateFormatSymbols().getShortWeekdays()[dayInt];
    }

    @Override
    public String convertTimeStampToTime(long time) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(time * 1000);
        String dayAndDate = DateFormat.format("h:mm a", cal).toString(); //should render Fri 09/17 11:26 AM
        return dayAndDate;
    }

    @Override
    public String convertTimeStampToDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(time * 1000);
        String dayAndDate = DateFormat.format("M/dd", cal).toString(); //should render Fri 09/17 11:26 AM
        return dayAndDate;
    }


    @Override
    public String formatTimeZoneToCity(String timeZone) {
        if (timeZone != null && !timeZone.equals("")) {
            return timeZone.substring(timeZone.indexOf('/') + 1).replace("_", " ");
        } else {
            return "World";
        }
    }

    @Override
    public String formatDoubleToStringDigit(double temp) {
        return "" + (int) Math.round(temp);
    }
}
