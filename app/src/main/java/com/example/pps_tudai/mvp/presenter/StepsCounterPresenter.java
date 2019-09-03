package com.example.pps_tudai.mvp.presenter;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import com.example.pps_tudai.data.entities.entity.User;
import com.example.pps_tudai.mvp.model.StepsCounterModel;
import com.example.pps_tudai.mvp.view.StepsCounterView;
import com.example.pps_tudai.utils.Tools;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import static com.example.pps_tudai.utils.IntUtils.ZERO;
import static com.example.pps_tudai.utils.StringUtils.FALSE;
import static com.example.pps_tudai.utils.StringUtils.TRUE;

public class StepsCounterPresenter implements SensorEventListener {

    private static final int ONE = 1;
    private final static int INTERVAL = 10000;
    private final static int FAST_INTERVAL = 5000;
    private final static float MARGIN_DISTANCE = 3;

    private final StepsCounterView counterView;
    private final StepsCounterModel counterModel;
    private User user;
    private SensorManager sensorManager;
    private Sensor sensor;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    public StepsCounterPresenter(StepsCounterView counterView, StepsCounterModel counterModel, int id,
                                 SensorManager sensorManager, FusedLocationProviderClient fusedLocationClient) {
        this.counterView = counterView;
        this.counterModel = counterModel;
        this.sensorManager = sensorManager;
        this.fusedLocationClient = fusedLocationClient;
        this.init(id);
    }

    @SuppressLint("MissingPermission")
    public void init(int id) {
        user = counterModel.getUserById(id);
        counterView.configSreen(user);

        createLocationRequest();
        setInitLocation();
        setLocationCallback();
    }

    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    public void onReturnPressed() {
        counterView.returnWelcomeActivity();
    }

    public void activeCounter() {
        counterModel.setMovement(TRUE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (sensor == null) {
            counterView.showNotSenserRegisteredMessagge();
        } else {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (counterModel.isMovement()) {
            int aux = counterModel.getAccumulated_steps();
            counterModel.setAccumulated_steps((int) sensorEvent.values[ZERO]);
            if (counterModel.getAccumulated_steps() > aux) {
                counterView.showCountingSteps(String.valueOf(counterModel.getSteps()));
                counterView.showConsumedCalories(String.valueOf(counterModel.getConsumedCalories()));
                counterModel.setSteps(counterModel.getSteps() + ONE);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not need
    }

    public void stopCounter() {
        sensorManager.unregisterListener(this);
        counterModel.setMovement(FALSE);
    }

    public SensorManager getSensorManager() {
        return sensorManager;
    }

    public void setSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public void showMessagge(String mensaje) {
        counterView.showMessagge(mensaje);
    }

    protected void setInitLocation() {
        @SuppressLint("MissingPermission") Task task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    counterModel.addNewLocation(location);
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    protected void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(INTERVAL);
        locationRequest.setFastestInterval(FAST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void setLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    setActualDistance(location);
                }
            }
        };
    }

    public void setActualDistance(Location location) {
        if (!counterModel.getLocations().isEmpty()) {
            Location last_location = counterModel.getLastLocation();
            Float extra_distance = last_location.distanceTo(location);
            if (extra_distance > MARGIN_DISTANCE ) {
                counterModel.setTravelledDistance(extra_distance);
            }

            counterView.showTravelledDistance(Tools.getRoundedDistance(counterModel.getDistance()));
            counterModel.addNewLocation(location);
        }
    }

    public void stopUpdateLocation() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}
