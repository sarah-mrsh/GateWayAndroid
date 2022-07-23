package com.example.fanp.presentation.modbus.tcp.tag;

import android.view.View;
import android.widget.AdapterView;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddTagTCPViewModel extends ViewModel {

    public String tagname;
    public String tagid;
    public String serverid;
    public String modbusaddres;

    public String functioncode;
    public String datatype;
    public String interval;
    public String description;


    AddTagTCP main;


    @Inject
    public AddTagTCPViewModel() {
    }

    @Inject
    I4AllSettingDao db;

    public void onSelectItemfunction(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        functioncode = parent.getAdapter().getItem(pos).toString();
    }


    public void onSelectItempdatattype(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        datatype = parent.getAdapter().getItem(pos).toString();
    }


//    public String tagname;
//    public String  tagid;
//    public String serverid;
//    public String modbusaddres;
//    public String functioncode;
//    public String datatype;
//    public int interval;
//    public String description;


    public void savedata() {
        JSONObject object = new JSONObject();
        try {
            object.put("tagname", tagname);
            object.put("tagid", tagid);
            object.put("serverid", serverid);
            object.put("modbusaddres", modbusaddres);
            object.put("functioncode", functioncode);
            object.put("datatype", datatype);
            object.put("interval", interval);
            object.put("description", description);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        List<I4AllSetting> data = new ArrayList<>();
        data = db.getitembyitesref(AddTagTCP.item.getAllSettingId());
        try {
            for (int i = 0; i < data.size(); i++) {
                JSONObject object1 = new JSONObject(data.get(i).getItemsData());
                if (object1.getString("tagid").equals(tagid)) { //update
                    data.get(i).setItemsData(object.toString());
                    db.update(data.get(i));
                    return;
                }
            }

            JSONArray array = new JSONArray(AddTagTCP.item.getItemsData());
            JSONObject jsonObject = array.getJSONObject(AddTagTCP.index);
            int deviceid = jsonObject.getInt("deviceid");
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            db.insert(new I4AllSetting(0, deviceid , object.toString(), false, timeStamp));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        main.finish();
    }

    public void exit(){
        main.finish();
    }


}
