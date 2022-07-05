package com.example.fanp.domain.local;

import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.data.UserDao;
import com.example.fanp.domain.local.data.WifiDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.domain.local.repository.User;
import com.example.fanp.domain.local.repository.WifiSetting;

@Database(entities = {User.class, I4AllSetting.class, WifiSetting.class}, version = 7 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {



    public abstract UserDao userDao();
    public abstract WifiDao wifiDao();



    public abstract I4AllSettingDao i4AllSettingDao();
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
