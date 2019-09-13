package com.example.pps_tudai.mvp.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import com.example.pps_tudai.mvp.model.MainModel;
import com.example.pps_tudai.mvp.view.MainView;
import com.example.pps_tudai.services.weatherService.WeatherAPIResponse;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pps_tudai.utils.IntUtils.LOCATION_REQUEST_CODE;

import static com.example.pps_tudai.utils.StringUtils.WEATHER_API_KEY;
import static com.example.pps_tudai.utils.StringUtils.WEATHER_UNITS;

public class MainPresenter implements GoogleApiClient.OnConnectionFailedListener{

    private final MainView mainView;
    private final MainModel mainModel;
    private LocationManager locationManager;
    private Call<WeatherAPIResponse> weatherCall;
    private WeatherAPIResponse weatherData;

    private GoogleApiClient googleApiClient;
    private CallbackManager callbackManager;

    public MainPresenter(MainView mainView, MainModel mainModel, GoogleApiClient googleApiClient, CallbackManager callbackManager) {
        this.mainView = mainView;
        this.mainModel = mainModel;
        this.googleApiClient = googleApiClient;
        this.callbackManager = callbackManager;
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
        mainView.showLoader();
        weatherCall = mainModel.getWeatherDataFromService(mainModel.getLatitude(), mainModel.getLongitude(),
                WEATHER_UNITS, WEATHER_API_KEY);

        weatherCall.enqueue(new Callback<WeatherAPIResponse>() {
            @Override
            public void onResponse(Call<WeatherAPIResponse> call, Response<WeatherAPIResponse> response) {
                if (!response.isSuccessful()) {
                    mainView.showContactAPINotSuccessful(String.valueOf(response.code()));
                    mainView.hideLoader();
                } else {
                    weatherData = response.body();
                    mainView.showWeatherData(weatherData);
                    mainView.hideLoader();
                }
            }

            @Override
            public void onFailure(Call<WeatherAPIResponse> call, Throwable t) {
                mainView.showContactAPIFailure(t.getMessage());
                mainView.hideLoader();
            }
        });
    }

    public void onLoginPressed() {
        mainView.showLoginScreen();
    }

    public void onRegistrationPressed() {
        mainView.showRegistrationScreen();
    }

    public void onLoginGooglePressed() {
        mainView.showLoginGoogleScreen(googleApiClient);
    }

    public void handleSingInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            mainView.showWelcomeSreen(result);
        } else {
            mainView.showConnectionGoogleFailed();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mainView.showConnectionGoogleFailed();
    }

    public void showConnectionFailedMessage() {
        mainView.showConnectionGoogleFailed();
    }

    public void showConnectionGoogleError() {
        mainView.showGoogleError();
    }

    public void onLoginFacebookPressed() {
        LoginManager.getInstance().logInWithReadPermissions(mainView.getActivity(), Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        mainView.showWelcomeSreen(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        mainView.showFacebookCancel();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        mainView.showFacebookError(exception);
                    }
                });
    }
}
