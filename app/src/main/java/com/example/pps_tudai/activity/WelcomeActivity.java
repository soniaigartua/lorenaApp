package com.example.pps_tudai.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pps_tudai.R;
import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.AppRoomDataBase;
import com.example.pps_tudai.mvp.model.WelcomeModel;
import com.example.pps_tudai.mvp.presenter.WelcomePresenter;
import com.example.pps_tudai.mvp.view.WelcomeView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.pps_tudai.utils.IntUtils.ZERO;
import static com.example.pps_tudai.utils.StringUtils.USER_ID;

public class WelcomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private WelcomePresenter presenter;
    private int userId;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        userId = getIntent().getExtras().getInt(USER_ID);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        configGoogleApiClient();
        WelcomeView welcomeView = new WelcomeView(this);
        AppRepository appRepository = new AppRepository(AppRoomDataBase.getDatabase(this).userDao());
        WelcomeModel welcomeModel = new WelcomeModel(appRepository);
        presenter = new WelcomePresenter(welcomeView,welcomeModel, userId);
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

    @OnClick(R.id.btn_logout)
    public void btnLogoutClicked() {
        presenter.onLogoutPressed();
    }

    @OnClick(R.id.image_user)
    public void selectAvatar() {
        presenter.onSelectAvatarPressed(userId);
    }

    @OnClick(R.id.exercise_button)
    public void btnExerciseClicked() {
        presenter.onExercisesListPressed();
    }

    @OnClick(R.id.counter_button)
    public void btnCounterStepsClicked() {
        presenter.onCounterStepsPressed();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "connection Failed", Toast.LENGTH_LONG).show();
    }
}
