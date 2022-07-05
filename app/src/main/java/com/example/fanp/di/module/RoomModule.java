package com.example.fanp.di.module;


import android.content.Context;

import androidx.room.Room;

import com.example.fanp.domain.local.AppDatabase;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.data.UserDao;
import com.example.fanp.domain.local.data.WifiDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {



    @Singleton
    @Provides
    public AppDatabase provideMyDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "my-db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public UserDao provideUserDao(AppDatabase myDatabase){
        return myDatabase.userDao();
    }


    @Singleton
    @Provides
    public WifiDao provideWifiDao(AppDatabase myDatabase){
        return myDatabase.wifiDao();
    }


    @Singleton
    @Provides
    public I4AllSettingDao provideI4AllSettingDao(AppDatabase myDatabase){
        return myDatabase.i4AllSettingDao();
    }



}
