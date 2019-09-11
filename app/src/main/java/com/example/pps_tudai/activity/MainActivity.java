package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pps_tudai.R;
import com.example.pps_tudai.mvp.model.MainModel;
import com.example.pps_tudai.mvp.presenter.MainPresenter;
import com.example.pps_tudai.mvp.view.MainView;
import com.example.pps_tudai.services.weatherService.WeatherServiceCall;
import com.example.pps_tudai.services.weatherService.WeatherServiceGenerator;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.example.pps_tudai.utils.IntUtils.SING_IN_CODE;
import static com.example.pps_tudai.utils.IntUtils.ZERO;
import static com.example.pps_tudai.utils.StringUtils.USER_ID;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private MainPresenter presenter;

    private GoogleApiClient googleApiClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        MainView view = new MainView(this);
        MainModel model = new MainModel(WeatherServiceGenerator.createService(WeatherServiceCall.class));
        presenter = new MainPresenter(view, model);

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
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, SING_IN_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SING_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSingInResult(result);
        }
        else {
            Toast.makeText(this, "the authentication is not available", Toast.LENGTH_LONG).show();
        }
    }

    private void handleSingInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            showMainScreen();
        } else {
            Toast.makeText(this, "connection not successful", Toast.LENGTH_LONG).show();
        }
    }

    private void showMainScreen() {
        Intent welcome = new Intent(this, WelcomeActivity.class);
        welcome.putExtra(USER_ID, ZERO);
        startActivity(welcome);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "connection Failed", Toast.LENGTH_LONG).show();
    }

}
