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
import com.example.pps_tudai.activity.WelcomeActivity;
import com.example.pps_tudai.services.weatherService.WeatherAPIResponse;

import com.facebook.FacebookException;

import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.pps_tudai.utils.IntUtils.GOOGLE_USER_ID;
import static com.example.pps_tudai.utils.IntUtils.PERCENTAGE_UPLOADER;
import static com.example.pps_tudai.utils.IntUtils.SING_IN_CODE;
import static com.example.pps_tudai.utils.IntUtils.SIZE_ICON_WEATHER;
import static com.example.pps_tudai.utils.IntUtils.ZERO;
import static com.example.pps_tudai.utils.StringUtils.DEGREES;
import static com.example.pps_tudai.utils.StringUtils.EMAIL_USER;
import static com.example.pps_tudai.utils.StringUtils.ICON_WEATHER_URL;
import static com.example.pps_tudai.utils.StringUtils.ICON_WEATHER_URL_END;
import static com.example.pps_tudai.utils.StringUtils.USER_EMAIL;
import static com.example.pps_tudai.utils.StringUtils.USER_ID;
import static com.example.pps_tudai.utils.StringUtils.USER_IMAGE;

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
        Picasso.get().load(ICON_WEATHER_URL + aux.getIcon() + ICON_WEATHER_URL_END)
                .resize(SIZE_ICON_WEATHER, SIZE_ICON_WEATHER)
                .into(img_weather);
        temp_actual.setText(String.valueOf(weather.getMain().getTemp()) + DEGREES);
    }

    public ProgressBar getProgress_bar_main() {
        return progress_bar_main;
    }

    public void setProgress_bar_main(ProgressBar progress_bar_main) {
        this.progress_bar_main = progress_bar_main;
    }

    public void showLoader() {
        progress_bar_main.setProgress(PERCENTAGE_UPLOADER);
        progress_bar_main.setVisibility(View.VISIBLE);
    }

    public void hideLoader() {
        progress_bar_main.setVisibility(View.GONE);
    }

    public void showLoginGoogleScreen(GoogleApiClient googleApiClient) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        getActivity().startActivityForResult(signInIntent, SING_IN_CODE);
    }

    public void showConnectionGoogleFailed() {
        Toast.makeText(activityWeak.get(), R.string.connection_failed, Toast.LENGTH_LONG).show();
    }

    public void showWelcomeSreen(GoogleSignInResult result) {
        GoogleSignInAccount account = result.getSignInAccount();

        Intent welcome = new Intent(activityWeak.get(), WelcomeActivity.class);
        welcome.putExtra(USER_ID, GOOGLE_USER_ID);
        welcome.putExtra(USER_EMAIL, account.getEmail());
        welcome.putExtra(USER_IMAGE, String.valueOf(account.getPhotoUrl()));
        activityWeak.get().startActivity(welcome);
    }

    public void showGoogleError() {
        Toast.makeText(activityWeak.get(), R.string.connection_not_available, Toast.LENGTH_LONG).show();
    }

    public void showFacebookError(FacebookException exception) {
        Toast.makeText(getActivity(), "ERROR " + exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void showFacebookCancel() {
        Toast.makeText(getActivity(), "CANCEL ", Toast.LENGTH_LONG).show();
    }

    public void showWelcomeSreen(LoginResult loginResult) {
        Intent welcome = new Intent(activityWeak.get(), WelcomeActivity.class);
        welcome.putExtra(USER_ID,  ZERO);
        welcome.putExtra(USER_EMAIL, EMAIL_USER);
//        welcome.putExtra(USER_IMAGE, null);
        activityWeak.get().startActivity(welcome);
    }
}