package com.example.pps_tudai.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherServiceCall {

    @GET("/data/2.5/weather")
    Call<WeatherAPIResponse> getData(@Query("lat") double lat, @Query("lon") double lon,
                                     @Query("units") String units, @Query("APPID") String apiKey);
}
