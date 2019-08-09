package com.example.pps_tudai.data.entities.asyncTask;

import android.os.AsyncTask;
import com.example.pps_tudai.data.entities.dao.UserDAO;
import com.example.pps_tudai.data.entities.entity.User;

import static com.example.pps_tudai.utils.IntUtils.ZERO;

public class InsertAsyncTask extends AsyncTask<User, Void, Void> {

    private UserDAO asyncTaskDao;

    public InsertAsyncTask(UserDAO dao) {
        this.asyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(User... params) {
        asyncTaskDao.insertUser(params[ZERO]);
        return null;
    }
}
