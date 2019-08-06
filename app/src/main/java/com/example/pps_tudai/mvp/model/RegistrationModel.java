package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.entity.User;

public class RegistrationModel {

    private final AppRepository usersRepository;
    private User user;

    public RegistrationModel(AppRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void registerUser(User user) {
        usersRepository.insert(user);
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        user = user;
    }
}
