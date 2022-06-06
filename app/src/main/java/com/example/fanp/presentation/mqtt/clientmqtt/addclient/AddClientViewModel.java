package com.example.fanp.presentation.mqtt.clientmqtt.addclient;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddClientViewModel extends ViewModel {

    @Inject
    I4AllSettingDao db;

    @Inject
    Context ctx;


    AddClient main;

    public String clientname;
    public String username;
    public String protocol;
    public String reconnect;
    public String qos;
    public String willtopic;
    public String clientid;
    public String ip;
    public String port;
    public String password;
    public String timeout;
    public String sessiontime;
    public boolean sendtimestamp;
    public boolean keepalive;
    public boolean compatibleversion;
    public boolean maintainewill;
    public boolean willcardsub;


    @Inject
    public AddClientViewModel() {

    }

    public void finish() {
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

    public void savedata() {
        JSONObject object = new JSONObject();
        try {
            object.put("clientname", clientname);
            object.put("username", username);
            object.put("protocol", protocol);
            object.put("reconnect", reconnect);
            object.put("qos", qos);
            object.put("willtopic", willtopic);
            object.put("clientid", clientid);
            object.put("ip", ip);
            object.put("port", port);
            object.put("password", password);
            object.put("timeout", timeout);
            object.put("sessiontime", sessiontime);
            object.put("sendtimestamp", sendtimestamp);
            object.put("keepalive", keepalive);
            object.put("compatibleversion", compatibleversion);
            object.put("maintainewill", maintainewill);
            object.put("willcardsub", willcardsub);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<I4AllSetting> data = db.getmqttclient();

        for (int i = 0; i < data.size(); i++) {
            try {
                JSONObject object1 = new JSONObject(data.get(i).getItemsData());
                if (object1.getString("clientid").equals(clientid)) {//update
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                    data.get(i).setDateTime(timeStamp);
                    data.get(i).setItemsData(object.toString());
                    db.update(data.get(i));
                    main.finish();
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        I4AllSetting ndata = new I4AllSetting(0, 517, object.toString(), false, timeStamp);
        db.insert(ndata);

        main.finish();

    }

}
