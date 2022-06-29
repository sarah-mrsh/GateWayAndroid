package com.example.fanp.presentation.protocolmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMprotocolManagmentBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.presentation.ble.BleViewModel;
import com.example.fanp.presentation.modbus.rtu.MainModBusRTU;
import com.example.fanp.presentation.modbus.tcp.MainModbusTCP;
import com.example.fanp.presentation.mqtt.broker.brokersetting.BrokerSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.MainMqttClient;
import com.example.fanp.presentation.s7.MainS7;

import java.util.Locale;

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
        viewmodel.main = this;
        setContentView(binding.getRoot());


    }


}

//
//        view.findViewById(R.id.txtmodbusrtu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(xcs, MainModBusRTU.class));
//            }
//        });
//        view.findViewById(R.id.txts7).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(xcs, MainS7.class));
//            }
//        });
//
//        view.findViewById(R.id.txtmqttclient).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(xcs, MainMqttClient.class));
//            }
//        });

