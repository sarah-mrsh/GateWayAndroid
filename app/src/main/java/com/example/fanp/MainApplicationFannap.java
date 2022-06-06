package com.example.fanp;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.fanp.di.componet.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MainApplicationFannap extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {


        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);


        return DaggerAppComponent.builder().application(this).build();
    }
}

