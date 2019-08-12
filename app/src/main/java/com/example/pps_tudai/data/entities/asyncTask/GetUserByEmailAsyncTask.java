package com.example.pps_tudai.data.entities.asyncTask;

import android.os.AsyncTask;
import com.example.pps_tudai.data.entities.dao.UserDAO;
import com.example.pps_tudai.data.entities.entity.User;
import java.util.concurrent.ExecutionException;

import static com.example.pps_tudai.utils.IntUtils.ZERO;

public class GetUserByEmailAsyncTask extends AsyncTask<String, Void, User> implements DbGenericQuery<User, String> {

    private UserDAO asyncTaskDao;

    public GetUserByEmailAsyncTask(UserDAO dao) {
        this.asyncTaskDao = dao;
    }

    @Override
    protected User doInBackground(String... strings) {
        return asyncTaskDao.findUserByEmail(strings[ZERO]);
    }

    @Override
    public User executeQuery(String email) {
        try {
            return this.execute(email).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
