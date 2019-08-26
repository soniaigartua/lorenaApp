package com.example.pps_tudai.data.entities;

import android.os.AsyncTask;

import com.example.pps_tudai.data.entities.asyncTask.ClearDataBaseAsyncTask;
import com.example.pps_tudai.data.entities.asyncTask.DataUpdateUser;
import com.example.pps_tudai.data.entities.asyncTask.GetUserByEmailAsyncTask;
import com.example.pps_tudai.data.entities.asyncTask.GetUserByIdAsyncTask;
import com.example.pps_tudai.data.entities.asyncTask.InsertAsyncTask;
import com.example.pps_tudai.data.entities.asyncTask.UpdateUserImageUrl;
import com.example.pps_tudai.data.entities.dao.UserDAO;
import com.example.pps_tudai.data.entities.entity.User;

import java.util.List;

public class AppRepository {

    private UserDAO userDao;

    public AppRepository(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void insert(User user) {
        new InsertAsyncTask(userDao).execute(user);
    }

    public User getUserByEmail(String email) {
        return new GetUserByEmailAsyncTask(userDao).executeQuery(email);
    }

    public User getUserById(int id) {
        return new GetUserByIdAsyncTask(userDao).executeQuery(id);
    }

    public void updateUser(int id, String url) {
        DataUpdateUser data = new DataUpdateUser(id, url);
        new UpdateUserImageUrl(userDao).executeQuery(data);
    }

    public void clearDatabase() {
        new ClearDataBaseAsyncTask(userDao).execute();
    }
}
