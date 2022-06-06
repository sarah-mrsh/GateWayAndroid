package com.example.fanp.presentation.modbus.tcp;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.tcp.serverlist.ServerList;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class MainModeBusTCPViewModel extends ViewModel {


    public String devicename;
    public String deviceid;
    public String ip;
    public String port;
    boolean dhcp;

    public MainModbusTCP main;

    @Inject
    I4AllSettingDao db;

    @Inject
    public MainModeBusTCPViewModel(){}


    public void savedata() {
        JSONObject object = new JSONObject();
        try {
            object.put("devicename", devicename);
            object.put("deviceid", deviceid);
            object.put("ip", ip);
            object.put("port", port);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        I4AllSetting data = db.getitembyId(505);
        if (data==null){
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data = new I4AllSetting(0,505,object.toString(),false,timeStamp);
            db.insert(data);
        }else{ // update data
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data.setDateTime(timeStamp);
            data.setItemsData(object.toString());
            db.update(data);
        }
    }

    public void exit() {

        main.finish();
    }

    public void serverlist() {
        main.startActivity(new Intent(main, ServerList.class));
    }


}
