package org.krishan.weatherapp.utils;

public interface StringConverter {
    String convertTimeStampToDay(long time);

    String convertTimeStampToTime(long time);

    String convertTimeStampToDate(long time);

    String formatTimeZoneToCity(String timeZone);

    String formatDoubleToStringDigit(double temp);
}
