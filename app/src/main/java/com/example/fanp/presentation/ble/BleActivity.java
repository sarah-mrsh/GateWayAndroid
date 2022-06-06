package com.example.fanp.presentation.ble;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityBleBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.presentation.account.AccountUser;
import com.example.fanp.presentation.account.AccountUserViewmodel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BleActivity extends DaggerAppCompatActivity {


    ActivityBleBinding binding;
    BleViewModel viewmodel;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ble);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(BleViewModel.class);
        binding.setViewmodel(viewmodel);
        setContentView(binding.getRoot());


    }



}