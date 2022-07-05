package com.example.fanp.presentation.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAccountBinding;

import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.presentation.main.MainViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class AccountUser extends DaggerAppCompatActivity {


    ActivityAccountBinding binding;
    AccountUserViewmodel viewmodel;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AccountUserViewmodel.class);
        binding.setViewmodel(viewmodel);
        setContentView(binding.getRoot());
    }




}