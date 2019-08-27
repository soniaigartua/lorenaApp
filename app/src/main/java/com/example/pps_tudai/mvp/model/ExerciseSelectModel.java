package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.services.exerciseService.ExerciseAPIResponse;
import com.example.pps_tudai.services.exerciseService.ExerciseServiceCall;

import retrofit2.Call;

public class ExerciseSelectModel {

    private ExerciseServiceCall exerciseService;

    public ExerciseSelectModel(ExerciseServiceCall exerciseService) {
        this.exerciseService = exerciseService;
    }

    public Call<ExerciseAPIResponse> getExercisesDataFromService(int limit, String format) {
        return exerciseService.getData(limit, format);
    }

    public Call<ExerciseAPIResponse> getWorkoutDataFromService(int limit, String format) {
        return getWorkoutService().getData(limit, format);
    }

    public ExerciseServiceCall getWorkoutService() {
        return exerciseService;
    }
}
