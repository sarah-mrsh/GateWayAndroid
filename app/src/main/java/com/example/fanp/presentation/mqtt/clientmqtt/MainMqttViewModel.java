package com.example.fanp.presentation.mqtt.clientmqtt;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.mqtt.clientmqtt.addclient.AddClient;

import javax.inject.Inject;

public class MainMqttViewModel extends ViewModel {


    @Inject
    Context ctx;


    MainMqttClient main;


    @Inject
    public MainMqttViewModel(){}



    public void add(){
        ctx.startActivity(new Intent(ctx, AddClient.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    public void exit(){
main.finish();
    }
}
