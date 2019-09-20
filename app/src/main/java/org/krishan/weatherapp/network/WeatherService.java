package org.krishan.weatherapp.network;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * interface containing Api endpoints and method signatures which contain Paths or Queries
 */
public interface WeatherService {
    @GET("forecast/{key}/{latitude},{longitude}")
    Single<ForecastModel> getRemoteForecast(@Path("key") String key,
                                            @Path("latitude") double latitude,
                                            @Path("longitude") double longitude);
}
