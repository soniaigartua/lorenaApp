package com.example.pps_tudai.data.entities.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.pps_tudai.data.entities.entity.User;
import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    long insertUser(User user);

    @Query("SELECT * FROM users_table WHERE id LIKE :id ")
    User findUserById(int id);

    @Query("SELECT * FROM users_table WHERE email LIKE :email ")
    User findUserByEmail(String email);

    @Query("SELECT * FROM users_table WHERE email LIKE :email AND password LIKE :password")
    User findUserByEmailAndPassword(String email, String password);

    @Query("UPDATE users_table SET url_image = :imageUrl WHERE id == :userId")
    public void updateImageUrl(int userId, String imageUrl);
}
