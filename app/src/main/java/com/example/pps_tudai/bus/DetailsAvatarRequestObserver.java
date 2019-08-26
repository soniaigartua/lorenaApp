package com.example.pps_tudai.bus;

import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;

public abstract class DetailsAvatarRequestObserver extends BusObserver<DetailsAvatarRequestObserver.DetailsAvatarRequestPressed> {

    public DetailsAvatarRequestObserver() {
        super(DetailsAvatarRequestPressed.class);
    }

    public static class DetailsAvatarRequestPressed {

        private AvatarAPIResponse.Result avatar;

        public DetailsAvatarRequestPressed(AvatarAPIResponse.Result avatar) {
            this.avatar = avatar;
        }

        public AvatarAPIResponse.Result getAvatar() {
            return avatar;
        }

        public void setAvatar(AvatarAPIResponse.Result avatar) {
            this.avatar = avatar;
        }
    }
}