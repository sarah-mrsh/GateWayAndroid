package com.example.fanp.presentation.mqtt.clientmqtt.taglist;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.modbus.rtu.taglist.TagListViewModel;
import com.example.fanp.presentation.mqtt.clientmqtt.addtag.AddTagMqttClient;

import javax.inject.Inject;

public class TagListMqttViweModel extends ViewModel {


    public TagListMqttClient main;

    @Inject
    public TagListMqttViweModel(){}


    public void add(){
        main.startActivity(new Intent(main,AddTagMqttClient.class));
    }

    public void add_tag(){
        main.startActivity(new Intent(main, AddTagMqttClient.class));
    }

    public void finish(){
        main.finish();
    }
}
