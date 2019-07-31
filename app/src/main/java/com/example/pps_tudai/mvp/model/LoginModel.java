package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.data.entities.User;

public class LoginModel {

    private final User user;

    public LoginModel() {
        User user = new User("lorena@example.com", "123456");
        this.user = user;
    }

    public boolean authenticateUser(String email, String password) {
        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
