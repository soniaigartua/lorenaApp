package com.example.pps_tudai.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.pps_tudai.R;
import com.example.pps_tudai.mvp.model.MainModel;
import com.example.pps_tudai.mvp.presenter.MainPresenter;
import com.example.pps_tudai.mvp.view.MainView;
import com.example.pps_tudai.services.weatherService.WeatherServiceCall;
import com.example.pps_tudai.services.weatherService.WeatherServiceGenerator;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;

import static com.example.pps_tudai.utils.IntUtils.SING_IN_CODE;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private MainPresenter presenter;

    private GoogleApiClient googleApiClient;
    private CallbackManager callbackManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        configGoogleApiClient();
        callbackManager = CallbackManager.Factory.create();
        MainView view = new MainView(this);
        MainModel model = new MainModel(WeatherServiceGenerator.createService(WeatherServiceCall.class));
        presenter = new MainPresenter(view, model, googleApiClient, callbackManager);
    }

    public void configGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @OnClick(R.id.btn_login)
    public void btnLoginClicked() {
        presenter.onLoginPressed();
    }

    @OnClick(R.id.btn_signup)
    public void btnRegistrationClicked() {
        presenter.onRegistrationPressed();
    }

    @OnClick(R.id.btn_google)
    public void btnSingInGoogleClicked() {
        presenter.onLoginGooglePressed();
    }

    @OnClick(R.id.btn_facebook)
    public void btnSingInFacebookClicked() {
        presenter.onLoginFacebookPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SING_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            presenter.handleSingInResult(result);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        presenter.showConnectionFailedMessage();
    }
}
