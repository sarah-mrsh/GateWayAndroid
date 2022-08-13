package com.example.fanp.presentation.s7.addplc;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.utils.IdEdt;
import com.example.fanp.utils.IpEdt;
import com.example.fanp.utils.NameEdt;
import com.example.fanp.utils.PortEdt;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddPlcViewModel extends ViewModel {

    @Inject
    Context ctx;

    @Inject
    public AddPlcViewModel(){}

    @Inject
    I4AllSettingDao db;

    public String devicename;
    public String deviceid;
    public String ip;
    public String port;

    AddPlc main;




    public void exit(){
        main.finish();
    }


    public void validation(IpEdt ip, IdEdt id, PortEdt port, NameEdt name){
        if (!ip.valid) {
            Toast.makeText(ctx, "IP is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!name.confirm()){
            return;
        }
        if (!port.valid){
            Toast.makeText(ctx, "Port is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!id.valid){
            Toast.makeText(ctx, "ID is not valid.", Toast.LENGTH_SHORT).show();
            return ;
        }
        savedata();
    }

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

        List<I4AllSetting> data = db.getitembyitesref(519);
        if (data.size()==0){//force insert
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            I4AllSetting newdata = new I4AllSetting(0, 519, object.toString(), false, timeStamp);
            db.insert(newdata);
            exit();
            return;
        }
        for (I4AllSetting items:data){
            try {
                JSONObject itobjext = new JSONObject(items.getItemsData());
                if (itobjext.getString("deviceid").equals(deviceid)){//update
                    //TODO REPEAT DATA
                    items.setItemsData(object.toString());
                    db.update(items);
                    exit();
                    return;
                }
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                I4AllSetting newdata = new I4AllSetting(0, 519, object.toString(), false, timeStamp);
                db.insert(newdata);
                exit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        if (data==null){
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            data = new I4AllSetting(0,519,object.toString(),false,timeStamp);
//            db.insert(data);
//        }else{ // update data
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            data.setDateTime(timeStamp);
//            data.setItemsData(object.toString());
//            db.update(data);
//        }
        exit();
    }
}
