package com.example.pps_tudai.mvp.model;

import android.location.Location;

import com.example.pps_tudai.services.WeatherAPIResponse;
import com.example.pps_tudai.services.WeatherServiceCall;

import retrofit2.Call;

public class MainModel {

    private Location location;
    private WeatherServiceCall weatherService;

    public  MainModel (WeatherServiceCall weatherService) {
        this.weatherService = weatherService;
    }

    public void setLocationObject(Location location) {
        this.location = location;
    }

    public Call<WeatherAPIResponse> getWeatherDataFromService(double lat, double lon, String units, String apiKey) {
        return weatherService.getData(lat, lon, units, apiKey);
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }
}
