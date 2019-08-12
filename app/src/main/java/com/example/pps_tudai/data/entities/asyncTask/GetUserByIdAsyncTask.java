package com.example.pps_tudai.data.entities.asyncTask;

import android.os.AsyncTask;

import com.example.pps_tudai.data.entities.dao.UserDAO;
import com.example.pps_tudai.data.entities.entity.User;

import java.util.concurrent.ExecutionException;

import static com.example.pps_tudai.utils.IntUtils.ZERO;

public class GetUserByIdAsyncTask extends AsyncTask<Integer, Void, User> implements DbGenericQuery<User, Integer> {

    private UserDAO asyncTaskDao;

    public GetUserByIdAsyncTask(UserDAO dao) {
        this.asyncTaskDao = dao;
    }

    @Override
    protected User doInBackground(Integer... params) {
        return asyncTaskDao.findUserById(params[ZERO]);
    }

    @Override
    public User executeQuery(Integer id) {
        try {
            return this.execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
