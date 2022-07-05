package com.example.fanp.presentation.mqtt.broker.editbroker;


import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityEditBrokerBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.presentation.mqtt.broker.editbroker.EditBrokerViewModel;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class EditBroker extends DaggerAppCompatActivity {


    ActivityEditBrokerBinding binding;
    EditBrokerViewModel viewmodel;

    @Inject
    Context cxt;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_broker);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_broker);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(EditBrokerViewModel.class);
        binding.setViewmodel(viewmodel);
        //viewmodel.getdata(binding.recycleMqttBrokers);
        setContentView(binding.getRoot());


    }




}