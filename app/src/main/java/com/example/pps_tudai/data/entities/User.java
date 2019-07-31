package com.example.pps_tudai.data.entities;

import static com.example.pps_tudai.utils.StringUtils.EMPTY;

public class User {

    private String name;
    private String surname;
    private String email;
    private String password;

    public User (String email, String password) {
        this.name = EMPTY;
        this.surname = EMPTY;
        this.email = email;
        this.password = password;
    }

    public User (String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
