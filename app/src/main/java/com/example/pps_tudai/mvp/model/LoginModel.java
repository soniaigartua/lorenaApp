package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.entity.User;

import static com.example.pps_tudai.utils.IntUtils.ZERO;

public class LoginModel {

    private final AppRepository usersRepository;

    public LoginModel(AppRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User getUserByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }

    public boolean validateUserByEmailAndPassword(String email, String password) {
        User aux = usersRepository.getUserByEmail(email);
        if (aux != null) {
            if (aux.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public int getUserId(String email, String password) {
        User aux = usersRepository.getUserByEmail(email);
        if (aux != null) {
            return (int) aux.getId();
        }
        return ZERO;
    }

    public boolean checkEmailRegistered(String email) {
        User aux = usersRepository.getUserByEmail(email);
        if (aux != null) {
                return true;
        }
        return false;
    }

    public void clearDatabase() {
        usersRepository.clearDatabase();
    }
}
