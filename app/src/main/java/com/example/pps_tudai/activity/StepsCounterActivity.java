package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorManager;
import android.os.Bundle;
import com.example.pps_tudai.R;
import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.AppRoomDataBase;
import com.example.pps_tudai.mvp.model.StepsCounterModel;
import com.example.pps_tudai.mvp.presenter.StepsCounterPresenter;
import com.example.pps_tudai.mvp.view.StepsCounterView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.pps_tudai.utils.StringUtils.USER_ID;

public class StepsCounterActivity extends AppCompatActivity {

    private StepsCounterPresenter presenter;
    private int userId;
    private SensorManager sensorManager;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_counter);

        userId = getIntent().getExtras().getInt(USER_ID);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        final StepsCounterView counterView = new StepsCounterView(this);
        AppRepository appRepository = new AppRepository(AppRoomDataBase.getDatabase(this).userDao());
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        StepsCounterModel counterModel = new StepsCounterModel(appRepository);
        presenter = new StepsCounterPresenter(counterView, counterModel, userId, sensorManager, fusedLocationClient);
    }

    @OnClick(R.id.btn_return)
    public void btnReturnClicked() {
        presenter.stopCounter();
        presenter.stopUpdateLocation();
        presenter.onReturnPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.activeCounter();
        presenter.startLocationUpdates();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopCounter();
        presenter.stopUpdateLocation();
    }
}