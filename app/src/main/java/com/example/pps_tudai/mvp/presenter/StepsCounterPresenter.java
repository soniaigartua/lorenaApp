package com.example.pps_tudai.mvp.presenter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.pps_tudai.data.entities.entity.User;
import com.example.pps_tudai.mvp.model.StepsCounterModel;
import com.example.pps_tudai.mvp.view.StepsCounterView;

import static com.example.pps_tudai.utils.IntUtils.ZERO;
import static com.example.pps_tudai.utils.StringUtils.FALSE;
import static com.example.pps_tudai.utils.StringUtils.TRUE;

public class StepsCounterPresenter implements SensorEventListener {

    private static final int ONE = 1;
    private final StepsCounterView counterView;
    private final StepsCounterModel counterModel;
    private User user;
    SensorManager sensorManager;
    Sensor sensor;

    public StepsCounterPresenter(StepsCounterView counterView, StepsCounterModel counterModel, int id, SensorManager sensorManager) {
        this.counterView = counterView;
        this.counterModel = counterModel;
        this.init(id);
        this.sensorManager = sensorManager;
    }

    public void init(int id) {
        user = counterModel.getUserById(id);
        counterView.configSreen(user);
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
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (counterModel.isMovement()) {
            int aux = counterModel.getAccumulated_steps();
            counterModel.setAccumulated_steps((int) sensorEvent.values[ZERO]);
            if (counterModel.getAccumulated_steps() > aux) {
                counterView.showCountingSteps(String.valueOf(counterModel.getSteps()));
                counterModel.setSteps(counterModel.getSteps() + ONE);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void stopCounter() {
        sensorManager.unregisterListener(this);
        counterModel.setMovement(FALSE);
    }
}
