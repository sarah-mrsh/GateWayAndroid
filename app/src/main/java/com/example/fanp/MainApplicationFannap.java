package com.example.fanp;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.fanp.di.componet.DaggerAppComponent;

import java.util.Locale;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MainApplicationFannap extends DaggerApplication {


//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(MyContextWrapper.wrap(base,"fa"));
//    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);
        return DaggerAppComponent.builder().application(this).build();
    }
}

