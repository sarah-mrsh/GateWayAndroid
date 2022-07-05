package com.example.fanp.presentation.s7.tag.memory;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.utils.IdEdt;
import com.example.fanp.utils.NameEdt;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddTagMemoryViewModel extends ViewModel {

    public String tagname;
    public String tagid;
    public String plc;
    public String type;
    public String address;
    public String bitnumber;
    public String function;
    public String description;


    public AddTagMemory main;

    @Inject
    Context ctx;

    @Inject
    I4AllSettingDao db;

    @Inject
    public AddTagMemoryViewModel(){}


    public void validation(IdEdt id, NameEdt name){

        if (!name.valid){
            Toast.makeText(ctx, "Name is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!id.valid){
            Toast.makeText(ctx, "ID is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }

        savedata();

    }

    public void savedata() {
        JSONObject object = new JSONObject();
        try {
            object.put("tagname", tagname);
            object.put("tagid", tagid);
            object.put("plc", plc);
            object.put("type", type);
            object.put("address", address);
            object.put("bitnumber", bitnumber);
            object.put("function", function);
            object.put("description", description);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<I4AllSetting> data = db.getitembyitesref(603);
        if (data.size()==0){//force insert
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            I4AllSetting newdata = new I4AllSetting(0, 603, object.toString(), false, timeStamp);
            db.insert(newdata);
            exit();
            return;
        }
        if (data.size()==0){
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            I4AllSetting newdata = new I4AllSetting(0, 603, object.toString(), false, timeStamp);
            db.insert(newdata);
            exit();
            return;
        }
        for (I4AllSetting items:data){
            try {
                JSONObject itobjext = new JSONObject(items.getItemsData());
                if (itobjext.getString("tagid").equals(tagid)){//update
                    //TODO REPEAT DATA
                    items.setItemsData(object.toString());
                    db.update(items);
                    exit();
                    return;
                }
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                I4AllSetting newdata = new I4AllSetting(0, 603, object.toString(), false, timeStamp);
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
    public void exit(){
        main.finish();
    }


    public void onSelectPlc(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        plc = parent.getAdapter().getItem(pos).toString();
    }

    public void onSelectType(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        type = parent.getAdapter().getItem(pos).toString();
    }

    public void onSelectFunction(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        function = parent.getAdapter().getItem(pos).toString();
    }




}
