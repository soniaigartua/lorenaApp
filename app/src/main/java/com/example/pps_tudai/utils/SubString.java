package com.example.pps_tudai.utils;

public class SubString {

    final static int FINAL_POS = 4;
    final static int ONE = 1;
    public static final Character SLASH = '/';
    public static final Character HYPHEN = '-';
    public static final Character SPACE = ' ';

    public static String isSubString(String image) {
        int initial_pos = image.lastIndexOf(SLASH) + ONE;
        String result = image.substring(initial_pos, image.length()-FINAL_POS);
        result.replace(HYPHEN, SPACE);
        return result;
    }
}

