package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.LoginActivity;
import com.example.pps_tudai.activity.RegistrationActivity;
import com.example.pps_tudai.services.weatherService.WeatherAPIResponse;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.pps_tudai.utils.IntUtils.PERCENTAGE_UPLOADER;
import static com.example.pps_tudai.utils.IntUtils.SIZE_ICON_WEATHER;
import static com.example.pps_tudai.utils.IntUtils.ZERO;
import static com.example.pps_tudai.utils.StringUtils.DEGREES;
import static com.example.pps_tudai.utils.StringUtils.ICON_WEATHER_URL;
import static com.example.pps_tudai.utils.StringUtils.ICON_WEATHER_URL_END;

public class MainView {

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;
    @BindView(R.id.img_weather)
    ImageView img_weather;
    @BindView(R.id.temp_actual)
    TextView temp_actual;
    @BindView(R.id.temp_description)
    TextView temp_description;
    @BindView(R.id.location)
    TextView signLocation;
    @BindView(R.id.progress_bar_main)
    ProgressBar progress_bar_main;

    public MainView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
    }

    public Activity getActivity() {
        return activityWeak.get();
    }

    public void showLoginScreen() {
        if (activityWeak.get() != null) {
            Intent login = new Intent(activityWeak.get(), LoginActivity.class);
            activityWeak.get().startActivity(login);
        }
    }

    public void showRegistrationScreen() {
        if (activityWeak.get() != null) {
            Intent register = new Intent(activityWeak.get(), RegistrationActivity.class);
            activityWeak.get().startActivity(register);
        }
    }

    public void showContactAPIError() {
        Toast.makeText(activityWeak.get(), R.string.error_contact_api_data, Toast.LENGTH_LONG).show();
    }

    public void showMensaje(String codigo) {
        Toast.makeText(activityWeak.get(), codigo, Toast.LENGTH_LONG).show();
    }

    public void showContactAPINotSuccessful(String codigo) {
        signLocation.setText(codigo);
    }

    public void showContactAPIFailure(String message) {
        signLocation.setText(message);
    }

    public void showWeatherData(WeatherAPIResponse weather) {
        WeatherAPIResponse.Weather aux = weather.getWeather().get(ZERO);
        temp_description.setText(aux.getDescription());
        Picasso.get().load(ICON_WEATHER_URL +  aux.getIcon() + ICON_WEATHER_URL_END)
                                        .resize(SIZE_ICON_WEATHER,SIZE_ICON_WEATHER)
                                        .into(img_weather);
        temp_actual.setText(String.valueOf(weather.getMain().getTemp()) + DEGREES);
    }

    public ProgressBar getProgress_bar_main() {
        return progress_bar_main;
    }

    public void setProgress_bar_main(ProgressBar progress_bar_main) {
        this.progress_bar_main = progress_bar_main;
    }

    public void showLoader () {
        progress_bar_main.setProgress(PERCENTAGE_UPLOADER);
        progress_bar_main.setVisibility(View.VISIBLE);
    }

    public void hideLoader () {
        progress_bar_main.setVisibility(View.GONE);
    }
}
