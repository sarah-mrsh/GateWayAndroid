package com.example.fanp.presentation.protocolmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMprotocolManagmentBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.presentation.ble.BleViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MProtocolManagment extends DaggerAppCompatActivity {

    ActivityMprotocolManagmentBinding binding;
    MprotocolManagmentViewModel viewmodel;


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mprotocol_managment);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MprotocolManagmentViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;
        setContentView(binding.getRoot());
    }
}