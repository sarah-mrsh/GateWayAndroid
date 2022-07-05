package com.example.fanp.presentation.modbus.tcp.serverlist;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.modbus.tcp.addserver.AddServertcp;

import javax.inject.Inject;

public class ServerLisViewMod extends ViewModel {


    ServerList main;

    @Inject
    public ServerLisViewMod(){}

    public void add(){
        main.startActivity(new Intent(main, AddServertcp.class));
    }


    public void exit(){
        main.finish();
    }

}
