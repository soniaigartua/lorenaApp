package com.example.pps_tudai.data.entities.asyncTask;

import android.os.AsyncTask;
import com.example.pps_tudai.data.entities.AppRoomDataBase;
import com.example.pps_tudai.data.entities.dao.UserDAO;

public class ClearDataBaseAsyncTask extends AsyncTask<Void, Void, Void> {

    private UserDAO asyncTaskDao;

    public ClearDataBaseAsyncTask(UserDAO dao) {
        this.asyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(Void... strings) {
        AppRoomDataBase.emptyDatabase();
        return null;
    }
}
