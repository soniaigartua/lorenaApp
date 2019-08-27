package com.example.pps_tudai.bus;

import androidx.appcompat.app.AlertDialog;

public abstract class CancelExerciseButtonDialogObserver extends BusObserver<CancelExerciseButtonDialogObserver.CancelExerciseButtonPressed> {

    public CancelExerciseButtonDialogObserver() {
        super(CancelExerciseButtonPressed.class);
    }

    public static class CancelExerciseButtonPressed {

        AlertDialog dialog;

        public CancelExerciseButtonPressed(AlertDialog dialog) {
            this.dialog = dialog;
        }

        public AlertDialog getDialog() {
            return dialog;
        }

        public void setDialog(AlertDialog dialog) {
            this.dialog = dialog;
        }
    }

}
