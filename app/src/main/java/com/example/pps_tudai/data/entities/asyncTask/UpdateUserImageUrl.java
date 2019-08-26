package com.example.pps_tudai.data.entities.asyncTask;

import android.os.AsyncTask;
import com.example.pps_tudai.data.entities.dao.UserDAO;

import static com.example.pps_tudai.utils.IntUtils.ZERO;

public class UpdateUserImageUrl extends AsyncTask<DataUpdateUser, Void, Void>
        implements DbGenericQuery<Void, DataUpdateUser> {

    private UserDAO userDao;

    public UpdateUserImageUrl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(DataUpdateUser... images) {
        DataUpdateUser image = images[ZERO];
        userDao.updateImageUrl(image.getUserId(), image.getImageUrl());
        return null;
    }

    @Override
    public Void executeQuery(DataUpdateUser user) {
        this.execute(user);
        return null;
    }
}
