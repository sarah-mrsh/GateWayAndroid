package com.example.fanp.di.componet;

import android.app.Application;


import com.example.fanp.MainApplicationFannap;
import com.example.fanp.di.module.ActivityBindingModule;
import com.example.fanp.di.module.AppModule;
import com.example.fanp.di.module.GrpcModule;
import com.example.fanp.di.module.RoomModule;
import com.example.fanp.di.module.ViewModelModule;
import com.example.fanp.di.module.ViewModelProviderModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules={
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ViewModelProviderModule.class,
        AppModule.class,
        ViewModelModule.class,
        RoomModule.class,
        GrpcModule.class
})

public interface AppComponent extends AndroidInjector<MainApplicationFannap> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }



    @Override
    void inject(MainApplicationFannap instance);
}
