package com.example.pps_tudai.services.avatarService;

import com.example.pps_tudai.services.weatherService.WeatherAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AvatarServiceCall {

    @GET("v1/public/characters")
    Call<AvatarAPIResponse> getData(@Query("ts") int ts, @Query("apikey") String apikey,
                             @Query("hash") String hash);
}
