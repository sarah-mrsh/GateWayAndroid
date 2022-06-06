package com.example.fanp.presentation.lan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityLanBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.presentation.account.AccountUserViewmodel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LanActivity extends DaggerAppCompatActivity {

    ActivityLanBinding binding;
    LanViewModel viewmodel;


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lan);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(LanViewModel.class);
        binding.setViewmodel(viewmodel);
        setContentView(binding.getRoot());
    }
}