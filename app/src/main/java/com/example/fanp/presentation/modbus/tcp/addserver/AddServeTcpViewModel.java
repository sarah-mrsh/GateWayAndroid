package com.example.fanp.presentation.modbus.tcp.addserver;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.utils.IdEdt;
import com.example.fanp.utils.IpEdt;
import com.example.fanp.utils.NameEdt;
import com.example.fanp.utils.PortEdt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class AddServeTcpViewModel extends ViewModel {


    public boolean isupdate = false;
    public String devicename;
    public String deviceid;
    public String ip;
    public String port;
    @Inject
    Context ctx;

    @Inject
    public AddServeTcpViewModel() {
    }

    AddServertcp main;

    I4AllSetting data;


    @Inject
    I4AllSettingDao db;


    public void exit() {
        main.finish();
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
        JSONArray datasave = new JSONArray();
        JSONObject object = new JSONObject();
        try {
            object.put("devicename", devicename);
            object.put("deviceid", deviceid);
            object.put("ip", ip);
            object.put("port", port);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        I4AllSetting data = db.gettcpmodbus();
        if (data == null) {
            datasave.put(object);
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data = new I4AllSetting(0, 509, datasave.toString(), false, timeStamp);
            db.insert(data);
        } else { // update data or add
            try {
                JSONArray array = new JSONArray(data.getItemsData());
                for (int i = 0; i < array.length(); i++) {
                    if (array.getJSONObject(i).getString("deviceid").equals(deviceid)) {//update
                        array.remove(i);
                        break;
                    }
                }
                array.put(object);
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                data.setDateTime(timeStamp);
                data.setItemsData(array.toString());
                db.update(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        main.finish();
    }


}
