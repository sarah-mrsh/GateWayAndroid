package com.example.fanp.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.example.fanp.di.injector.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelProviderModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);


}