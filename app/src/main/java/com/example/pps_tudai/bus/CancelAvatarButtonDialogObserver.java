package com.example.pps_tudai.bus;

import androidx.appcompat.app.AlertDialog;

public abstract class CancelAvatarButtonDialogObserver extends BusObserver<CancelAvatarButtonDialogObserver.CancelAvatarButtonPressed> {

    public CancelAvatarButtonDialogObserver() {
        super(CancelAvatarButtonPressed.class);
    }

    public static class CancelAvatarButtonPressed {

        AlertDialog dialog;

        public CancelAvatarButtonPressed(AlertDialog dialog) {
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
