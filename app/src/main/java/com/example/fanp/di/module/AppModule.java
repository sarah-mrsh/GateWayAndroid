package com.example.fanp.di.module;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.fanp.MyContextWrapper;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {


    @Inject
    Context ctx;


    @Provides
    Context provideContext(Application application) {
        Context context = MyContextWrapper.wrap(application/*in fragment use getContext() instead of this*/, "fa");
        application.getResources().updateConfiguration(context.getResources().getConfiguration(), context.getResources().getDisplayMetrics());
        return application;
    }


    @Provides
    public boolean hasNetwork ()
    {


        return checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork() // NOTE: this method added a network permission
    {
        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }






}
