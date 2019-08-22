package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;
import com.example.pps_tudai.services.avatarService.AvatarServiceCall;
import retrofit2.Call;

public class AvatarSelectModel {

    private AvatarServiceCall avatarService;


    public AvatarSelectModel(AvatarServiceCall avatarService) {
        this.avatarService = avatarService;
    }

    public Call<AvatarAPIResponse> getAvatarsDataFromService(int timestamp, String apiKey, String hash) {
        return avatarService.getData(timestamp, apiKey, hash);
    }
}
