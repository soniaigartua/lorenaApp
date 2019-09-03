package com.example.pps_tudai.mvp.model;

import android.location.Location;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.entity.User;

import java.util.ArrayList;

public class StepsCounterModel {

    private final static float INITIAL = 0;
    private static final float STEPS_PER_CALORIE = 20;

    private final AppRepository usersRepository;
    private boolean movement = false;
    private int steps;
    private int  accumulated_steps;

    private float travelled_distance  = INITIAL;
    ArrayList<Location> locations = new ArrayList<Location>();

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

    public float getDistance() {
        return travelled_distance;
    }

    public void setDistance(float distance) {
        this.travelled_distance = distance;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setTravelledDistance (float distance) {
        travelled_distance = travelled_distance + distance;
    }

    public void addNewLocation (Location location) {
        locations.add(location);
    }

    public Location getLastLocation () {
        return locations.get(locations.size()-1);
    }

    public int getConsumedCalories () {
        return Math.round(steps/STEPS_PER_CALORIE);
    }
}
