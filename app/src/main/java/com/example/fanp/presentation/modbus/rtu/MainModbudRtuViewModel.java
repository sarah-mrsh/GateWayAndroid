package com.example.fanp.presentation.modbus.rtu;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.rtu.serverlistrtu.ServerListRtu;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class MainModbudRtuViewModel extends ViewModel {


    @Inject
    public MainModbudRtuViewModel() {
    }

    public String devicename;
    public String deviceid;
    public String baudrate;
    public String startbit;
    public String databit;
    public String parity;
    public String endbit;

    @Inject
    I4AllSettingDao db;

    MainModBusRTU main;


    public void onSelectItembaud(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        baudrate = parent.getAdapter().getItem(pos).toString();
    }

    public void onSelectItemparity(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        parity = parent.getAdapter().getItem(pos).toString();
    }


    public void savedata() {
        JSONObject object = new JSONObject();
        try {
            object.put("devicename", devicename);
            object.put("deviceid", deviceid);
            object.put("baudrate", baudrate);
            object.put("startbit", startbit);
            object.put("databit", databit);
            object.put("parity", parity);
            object.put("endbit", endbit);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        I4AllSetting data = db.getitembyId(512);
        if (data==null){
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data = new I4AllSetting(0,512,object.toString(),false,timeStamp);
            db.insert(data);
        }else{ // update data
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data.setDateTime(timeStamp);
            data.setItemsData(object.toString());
            db.update(data);
        }
    }

    public void exit(){
main.finish();
    }

    public void serverlist(){
        I4AllSetting data = db.getitembyId(512);
        if (data==null){
            Toast.makeText(main, "Master Is Null", Toast.LENGTH_SHORT).show();
        }else
        main.startActivity(new Intent(main, ServerListRtu.class));

    }


}
