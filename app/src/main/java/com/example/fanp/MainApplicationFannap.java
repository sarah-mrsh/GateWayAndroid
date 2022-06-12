package com.example.fanp;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.fanp.di.componet.DaggerAppComponent;

import java.util.Locale;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MainApplicationFannap extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {


        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);

//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        android.content.res.Configuration conf = res.getConfiguration();
//        conf.setLocale(new Locale("fa".toLowerCase()));
//        res.updateConfiguration(conf, dm);
        return DaggerAppComponent.builder().application(this).build();
    }
}

