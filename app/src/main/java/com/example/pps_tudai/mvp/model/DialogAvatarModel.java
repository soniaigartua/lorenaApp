package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.entity.User;

public class DialogAvatarModel {

    private final AppRepository usersRepository;

    public DialogAvatarModel(AppRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void updateImageUrl(int userId, String imageUrl) {
        usersRepository.updateUser(userId, imageUrl);
    }

    public User getUserById(int id) {
        return usersRepository.getUserById(id);
    }
}
