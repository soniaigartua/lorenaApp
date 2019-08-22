package com.example.pps_tudai.data.entities.asyncTask;

public class DataUpdateUser {

    private int userId;
    private String imageUrl;

    public DataUpdateUser(int userId, String imageUrl) {
        this.userId = userId;
        this.imageUrl = imageUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
