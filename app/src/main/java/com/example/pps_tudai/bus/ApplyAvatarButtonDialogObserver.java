package com.example.pps_tudai.bus;

import androidx.appcompat.app.AlertDialog;

import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;


public abstract class ApplyAvatarButtonDialogObserver extends BusObserver<ApplyAvatarButtonDialogObserver.ApplyAvatarButtonPressed> {

    public ApplyAvatarButtonDialogObserver() {
        super(ApplyAvatarButtonPressed.class);
    }

    public static class ApplyAvatarButtonPressed {

        private AvatarAPIResponse.Result avatar;
        private AlertDialog dialog;

        public ApplyAvatarButtonPressed(AvatarAPIResponse.Result avatar, AlertDialog dialog) {
            this.avatar = avatar;
            this.dialog = dialog;
        }

        public AvatarAPIResponse.Result getAvatar() {
            return avatar;
        }

        public void setAvatar(AvatarAPIResponse.Result avatar) {
            this.avatar = avatar;
        }

        public AlertDialog getDialog() {
            return dialog;
        }

        public void setDialog(AlertDialog dialog) {
            this.dialog = dialog;
        }
    }

}
