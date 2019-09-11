package com.example.pps_tudai.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pps_tudai.R;
import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.AppRoomDataBase;
import com.example.pps_tudai.mvp.model.StepsCounterModel;
import com.example.pps_tudai.mvp.presenter.StepsCounterPresenter;
import com.example.pps_tudai.mvp.view.StepsCounterView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.pps_tudai.utils.IntUtils.ZERO;
import static com.example.pps_tudai.utils.StringUtils.USER_ID;

public class StepsCounterActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private StepsCounterPresenter presenter;
    private int userId;
    private SensorManager sensorManager;
    private FusedLocationProviderClient fusedLocationClient;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_counter);

        userId = getIntent().getExtras().getInt(USER_ID);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        configGoogleApiClient();
        final StepsCounterView counterView = new StepsCounterView(this);
        AppRepository appRepository = new AppRepository(AppRoomDataBase.getDatabase(this).userDao());
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        StepsCounterModel counterModel = new StepsCounterModel(appRepository);
        presenter = new StepsCounterPresenter(counterView, counterModel, userId, sensorManager, fusedLocationClient);
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

    @Override
    protected void onStart() {
        super.onStart();
        if (userId == ZERO) {
            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
            if (opr.isDone()) {
                GoogleSignInResult result = opr.get();
                handleSignInResult(result);
            }
            else {
                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                        handleSignInResult(googleSignInResult);
                    }
                });
            }
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            presenter.showUserData(account);
        }
    }

    @OnClick(R.id.btn_return)
    public void btnReturnClicked() {
        presenter.stopCounter();
        presenter.stopUpdateLocation();
        presenter.onReturnPressed();
    }

    @OnClick(R.id.btn_reset)
    public void btnResetClicked() {
        presenter.resetCounter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.activeCounter();
        presenter.startLocationUpdates();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "connection Failed", Toast.LENGTH_LONG).show();
    }
}