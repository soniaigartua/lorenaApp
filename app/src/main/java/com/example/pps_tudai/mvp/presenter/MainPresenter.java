package com.example.pps_tudai.mvp.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.example.pps_tudai.mvp.model.MainModel;
import com.example.pps_tudai.mvp.view.MainView;
import com.example.pps_tudai.services.weatherService.WeatherAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pps_tudai.utils.IntUtils.LOCATION_REQUEST_CODE;
import static com.example.pps_tudai.utils.StringUtils.WEATHER_API_KEY;
import static com.example.pps_tudai.utils.StringUtils.WEATHER_UNITS;

public class MainPresenter {

    private final MainView mainView;
    private final MainModel mainModel;
    private LocationManager locationManager;
    private Call<WeatherAPIResponse> weatherCall;
    private WeatherAPIResponse weatherData;

    public MainPresenter(MainView mainView, MainModel mainModel) {
        this.mainView = mainView;
        this.mainModel = mainModel;
        this.init();
    }

    public void init() {
        locationManager = (LocationManager) mainView.getActivity().getSystemService(Context.LOCATION_SERVICE);
        checkLocationPermission();
        callWeatherService();
    }

    public void checkLocationPermission() {

        if (ActivityCompat.checkSelfPermission(mainView.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mainView.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
            return;
        } else {
            mainModel.setLocationObject(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        }
    }

    public void callWeatherService() {
        mainView.showUploader();
        weatherCall = mainModel.getWeatherDataFromService(mainModel.getLatitude(), mainModel.getLongitude(),
                WEATHER_UNITS, WEATHER_API_KEY);

        weatherCall.enqueue(new Callback<WeatherAPIResponse>() {
            @Override
            public void onResponse(Call<WeatherAPIResponse> call, Response<WeatherAPIResponse> response) {
                if (!response.isSuccessful()) {
                    mainView.showContactAPINotSuccessful(String.valueOf(response.code()));
                    mainView.hideUploader();
                } else {
                    weatherData = response.body();
                    mainView.showWeatherData(weatherData);
                    mainView.hideUploader();
                }
            }

            @Override
            public void onFailure(Call<WeatherAPIResponse> call, Throwable t) {
                mainView.showContactAPIFailure(t.getMessage());
                mainView.hideUploader();
            }
        });
    }

    public void onLoginPressed() {
        mainView.showLoginScreen();
    }

    public void onRegistrationPressed() {
        mainView.showRegistrationScreen();
    }
}
