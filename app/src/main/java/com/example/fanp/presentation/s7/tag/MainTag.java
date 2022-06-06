package com.example.fanp.presentation.s7.tag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMainTagBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.presentation.s7.addplc.AddPlcViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainTag extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    ActivityMainTagBinding binding;
    MainTagViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_tag);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MainTagViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;


    }
}