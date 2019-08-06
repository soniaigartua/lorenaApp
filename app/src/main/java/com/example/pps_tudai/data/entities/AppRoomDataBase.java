package com.example.pps_tudai.data.entities;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pps_tudai.data.entities.entity.User;
import com.example.pps_tudai.data.entities.dao.UserDAO;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppRoomDataBase extends RoomDatabase {

    private static final String DB_NAME = "Users Data Base";
    private static volatile AppRoomDataBase INSTANCE;

    public abstract UserDAO userDao();

    public static AppRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDataBase.class, DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void emptyDatabase() {
        if (INSTANCE == null) {
            return;
        }
        INSTANCE.clearAllTables();
    }
}
