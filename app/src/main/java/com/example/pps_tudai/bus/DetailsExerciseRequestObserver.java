package com.example.pps_tudai.bus;

import com.example.pps_tudai.services.exerciseService.Result;

public abstract class DetailsExerciseRequestObserver extends BusObserver<DetailsExerciseRequestObserver.DetailsExerciseRequestPressed> {

    public DetailsExerciseRequestObserver() {
        super(DetailsExerciseRequestPressed.class);
    }

    public static class DetailsExerciseRequestPressed {

        private Result exercise;

        public DetailsExerciseRequestPressed(Result exercise) {
            this.exercise = exercise;
        }

        public Result getExercise() {
            return exercise;
        }

        public void setExercise(Result exercise) {
            this.exercise = exercise;
        }
    }
}