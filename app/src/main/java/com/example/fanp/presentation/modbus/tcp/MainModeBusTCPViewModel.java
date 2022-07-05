package com.example.fanp.presentation.modbus.tcp;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.tcp.serverlist.ServerList;
import com.example.fanp.utils.IdEdt;
import com.example.fanp.utils.IpEdt;
import com.example.fanp.utils.NameEdt;
import com.example.fanp.utils.PortEdt;

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
    Context ctx;

    @Inject
    I4AllSettingDao db;

    @Inject
    public MainModeBusTCPViewModel() {
    }

    public void validation(IpEdt ip, IdEdt id, PortEdt port, NameEdt name){
        if (!name.valid){
            Toast.makeText(ctx, "Name is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!id.valid){
            Toast.makeText(ctx, "ID is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!ip.valid){
            Toast.makeText(ctx, "IP is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!port.valid){
            Toast.makeText(ctx, "Port is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }
        savedata();
    }

    public void savedata() {
        if (!main.last_status){
            main.enable_items(true);
            return;
        }
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
        if (data == null) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data = new I4AllSetting(0, 505, object.toString(), false, timeStamp);
            db.insert(data);
        } else { // update data
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data.setDateTime(timeStamp);
            data.setItemsData(object.toString());
            db.update(data);
        }
        exit();
    }

    public void exit() {

        main.finish();
    }

    public void serverlist() {
        I4AllSetting data = db.getitembyId(505);
        if (data == null) {
            Toast.makeText(main, "Master Is Null", Toast.LENGTH_SHORT).show();
        } else
            main.startActivity(new Intent(main, ServerList.class));
    }


}
