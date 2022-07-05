package com.example.fanp.presentation.modbus.rtu.serverlistrtu;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.modbus.rtu.addserver.AddServerRtu;

import javax.inject.Inject;

public class ServerListRTUViewModel extends ViewModel {



    ServerListRtu main;


    @Inject
    public ServerListRTUViewModel(){}

    public void addserver(){
        main.startActivity(new Intent(main, AddServerRtu.class));
    }


    public void exit(){
        main.finish();
    }

}
