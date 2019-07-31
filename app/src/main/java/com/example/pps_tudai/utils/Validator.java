package com.example.pps_tudai.utils;

public class Validator {

    private static final String VALID_EMAIL_MODEL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    public boolean isValid(String email) {
        return email.matches(VALID_EMAIL_MODEL);
    }
}
