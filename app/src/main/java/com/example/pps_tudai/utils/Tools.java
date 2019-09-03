package com.example.pps_tudai.utils;

public class Tools {

    private static final String VALID_EMAIL_MODEL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    public static boolean isValid(String email) {
        return email.matches(VALID_EMAIL_MODEL);
    }

    public static String getRoundedDistance (Float distance) {
        return String.valueOf(Math.round(distance*100)/100.0d);
    }
}

