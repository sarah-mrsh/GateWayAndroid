package com.example.fanp.presentation.mqtt.mainpage;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMqttBrokersListBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MqttBrokers extends DaggerAppCompatActivity {


    @Inject
    Context cxt;

    @Inject
    ViewModelProviderFactory providerFactory;
    ActivityMqttBrokersListBinding binding;
    MqttBrokersViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mqtt_brokers_list);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MqttBrokersViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.getdata(binding.recycleMqttBrokers);
        setContentView(binding.getRoot());
    }

}