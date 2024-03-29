package com.example.pps_tudai.data.entities.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.example.pps_tudai.utils.StringUtils.EMPTY;

@Entity(tableName = "users_table")
public class User {

    @ColumnInfo(name = "id")
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private long id;
    @Nullable
    @ColumnInfo(name = "name")
    private String name;
    @Nullable
    @ColumnInfo(name = "surname")
    private String surname;
    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;
    @Nullable
    @ColumnInfo(name = "url_image")
    private String url_image;


    public User (@Nullable String name, @Nullable String surname, String email, String password) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.url_image = null;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(@Nullable String url_image) {
        this.url_image = url_image;
    }
}
