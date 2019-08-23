package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;
import com.example.pps_tudai.services.avatarService.AvatarServiceCall;
import retrofit2.Call;

public class AvatarSelectModel {

    private AvatarServiceCall avatarService;
    private final AppRepository usersRepository;

    public AvatarSelectModel(AppRepository usersRepository, AvatarServiceCall avatarService) {
        this.usersRepository = usersRepository;
        this.avatarService = avatarService;
    }

    public Call<AvatarAPIResponse> getAvatarsDataFromService(int timestamp, String apiKey, String hash) {
        return avatarService.getData(timestamp, apiKey, hash);
    }

    public void updateImageUrl(int userId, String imageUrl) {
        usersRepository.updateUser(userId, imageUrl);
    }
}
