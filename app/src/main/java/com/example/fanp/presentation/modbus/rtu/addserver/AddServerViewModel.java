package com.example.fanp.presentation.modbus.rtu.addserver;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class AddServerViewModel extends ViewModel {


    @Inject
    I4AllSettingDao db;

    AddServerRtu main;


    @Inject
    public AddServerViewModel(){}



    public String devicename;
    public String deviceid;
    public String modbusadress;


    public void savedata() {
        JSONArray datasave = new JSONArray();
        JSONObject object = new JSONObject();
        try {
            object.put("devicename", devicename);
            object.put("deviceid", deviceid);
            object.put("modbusadress", modbusadress);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        I4AllSetting data = db.getrtumodbusserverlist();
        if (data == null) {
            datasave.put(object);
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data = new I4AllSetting(0, 513, datasave.toString(), false, timeStamp);
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

    public void exit(){
        main.finish();
    }

}
