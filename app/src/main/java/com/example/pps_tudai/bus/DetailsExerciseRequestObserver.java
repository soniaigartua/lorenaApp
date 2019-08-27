package com.example.pps_tudai.bus;

import com.example.pps_tudai.services.exerciseService.ExerciseAPIResponse;

public abstract class DetailsExerciseRequestObserver extends BusObserver<DetailsExerciseRequestObserver.DetailsExerciseRequestPressed> {

    public DetailsExerciseRequestObserver() {
        super(DetailsExerciseRequestPressed.class);
    }

    public static class DetailsExerciseRequestPressed {

        private ExerciseAPIResponse.Result exercise;

        public DetailsExerciseRequestPressed(ExerciseAPIResponse.Result exercise) {
            this.exercise = exercise;
        }

        public ExerciseAPIResponse.Result getExercise() {
            return exercise;
        }

        public void setExercise(ExerciseAPIResponse.Result exercise) {
            this.exercise = exercise;
        }
    }
}