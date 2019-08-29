package com.example.pps_tudai.mvp.model;

import android.hardware.SensorManager;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.entity.User;

public class StepsCounterModel {

    private final AppRepository usersRepository;
    private boolean movement = false;
    private int steps;
    private int  accumulated_steps;

    public StepsCounterModel(AppRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User getUserById(int id) {
        return usersRepository.getUserById(id);
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public boolean isMovement() {
        return movement;
    }

    public void setMovement(boolean movement) {
        this.movement = movement;
    }

    public int getAccumulated_steps() {
        return accumulated_steps;
    }

    public void setAccumulated_steps(int accumulated_steps) {
        this.accumulated_steps = accumulated_steps;
    }
}
