package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.entity.User;

public class WelcomeModel {

    private final AppRepository usersRepository;

    public WelcomeModel(AppRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User getUserById(int id) {
        return usersRepository.getUserById(id);
    }
}
