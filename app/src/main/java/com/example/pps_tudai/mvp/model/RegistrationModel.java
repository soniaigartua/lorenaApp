package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.data.entities.User;

public class RegistrationModel {

    public void registerUser(String name, String surname, String email, String password) {
        User user = new User(name, surname, email, password);
    }
}
