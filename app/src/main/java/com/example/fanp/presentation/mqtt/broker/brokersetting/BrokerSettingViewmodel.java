package com.example.fanp.presentation.mqtt.broker.brokersetting;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.broker.clientlist.ClientList;
import com.example.fanp.presentation.mqtt.broker.taglist.Localbrokertaglist;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class BrokerSettingViewmodel extends ViewModel {




    BrokerSetting main;

    @Inject
    I4AllSettingDao db;

    @Inject
    Context ctx;


    public String brokername;
    public String username;
    public String protocol;
    public String maxclient;
    public String qos;
    public String retainmessage;
    public String brokerid;
    public String ip;
    public String port;
    public String password;
    public String maxlenght;
    public String maxque;
    public String sessiontime;
    public boolean sendtimestamp;
    public boolean keepalive;
    public boolean compatibleversion;
    public boolean maintainewill;
    public boolean willcardsub;


    @Inject
    public BrokerSettingViewmodel() {
    }


    public void savedata() {
        JSONObject object = new JSONObject();
        try {
            object.put("brokername", brokername);
            object.put("username", username);
            object.put("protocol", protocol);
            object.put("maxclient", maxclient);
            object.put("qos", qos);
            object.put("retainmessage", retainmessage);
            object.put("brokerid", brokerid);
            object.put("ip", ip);
            object.put("port", port);
            object.put("password", password);
            object.put("maxlenght", maxlenght);
            object.put("maxque", maxque);
            object.put("sessiontime", sessiontime);
            object.put("sendtimestamp", sendtimestamp);
            object.put("keepalive", keepalive);
            object.put("compatibleversion", compatibleversion);
            object.put("maintainewill", maintainewill);
            object.put("willcardsub", willcardsub);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        I4AllSetting data = db.getitembyId(500);
        if (data==null){
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data = new I4AllSetting(0,500,object.toString(),false,timeStamp);
            db.insert(data);
        }else{ // update data
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data.setDateTime(timeStamp);
            data.setItemsData(object.toString());
            db.update(data);
        }

        main.finish();

    }

    public void managmenttag(){ ctx.startActivity(new Intent(ctx, Localbrokertaglist.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); }
    public void access(){ ctx.startActivity(new Intent(ctx, ClientList.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); }


    public void finish(){
        main.finish();
    }


    public void onSelectItemprotocol(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        protocol = parent.getAdapter().getItem(pos).toString();
    }

    public void onSelectItemqos(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        qos = parent.getAdapter().getItem(pos).toString();
    }
}
