package com.example.fanp.presentation.s7.manageplc.adddatablockplc;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddDataBlockPlcViewModel extends ViewModel {


    public String name;
    public String plcname;
    public String id;
    public String functionblock;
    public String description;


    @Inject
    I4AllSettingDao db;

    @Inject
    Context ctx;


    @Inject
    public AddDataBlockPlcViewModel() {}


    public void onSelectfunctioncode(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        functionblock = parent.getAdapter().getItem(pos).toString();
    }

    public void onSelectPlc(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        plcname = parent.getAdapter().getItem(pos).toString();
    }


//    public String name;
//    public String plcname;
//    public String id;
//    public String functionblock;
//    public String description;

    public void savedata() {
        JSONObject object = new JSONObject();
        try {
            object.put("name", name);
            object.put("plcname", plcname);
            object.put("id", id);
            object.put("functionblock", functionblock);
            object.put("description", description);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<I4AllSetting> spdata = db.getplc();
        int plcid = 0;
        for (I4AllSetting item : spdata) {
            try {
                JSONObject temp = new JSONObject(item.getItemsData());
                if (temp.getString("devicename").equals(plcname)) {
                    plcid = temp.getInt("deviceid");
                    break;
                }
            } catch (JSONException e) {
                Toast.makeText(ctx, "db error in read plc id", Toast.LENGTH_SHORT).show();
            }
        }

//        I4AllSetting data = db.getitembyId(plcid);
        I4AllSetting data;
//        if (data==null){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        data = new I4AllSetting(0, plcid, object.toString(), false, timeStamp);
        db.insert(data);
//        }else{ // update data
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            data.setDateTime(timeStamp);
//            data.setItemsData(object.toString());
//            db.update(data);
//        }
    }

}
