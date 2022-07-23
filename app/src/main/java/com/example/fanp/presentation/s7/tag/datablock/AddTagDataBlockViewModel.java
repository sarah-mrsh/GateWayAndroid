package com.example.fanp.presentation.s7.tag.datablock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
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

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddTagDataBlockViewModel extends ViewModel {

    public String tagname;
    public String tagid;
    public String datablockparameter;//parameter id
    public String functionblocknumber;//functionblocknumber id
    public String dbid;//datablockid id
    public String function;
    public String datablockcount;
    public String description;

    public String name;
    public String type;
    public String address;
    public String bitnumber;
    public String parameterid;
    public String paramdescription;

    @Inject
    Context ctx;

    @Inject
    I4AllSettingDao db;


    @SuppressLint("StaticFieldLeak")
    AddDatatBlockTag main;

    @Inject
    public AddTagDataBlockViewModel() {
    }


    public void exit() {
        main.finish();
    }



    public void onSelectfunctioncode(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        function = parent.getAdapter().getItem(pos).toString();
    }

    public void validation(IdEdt id, NameEdt name){


        if (!name.valid){
            Toast.makeText(ctx,"Name is not valid.",Toast.LENGTH_LONG).show();
            return ;

        }

        if (!id.valid){
            Toast.makeText(ctx, "ID is not valid.", Toast.LENGTH_SHORT).show();
            return ;
        }

        savedata();
    }

    public void savedata() {
        try {
            JSONObject object = new JSONObject();

            object.put("tagname", tagname);
            object.put("tagid", tagid);
            object.put("parameter_id", datablockparameter);
            object.put("functionblock_id", functionblocknumber);
            object.put("dbid", dbid);
            object.put("function", function);
            object.put("datablockcount", datablockcount);
            object.put("description", description);


            object.put("name", name);
            object.put("type", type);
            object.put("address", address);
            object.put("bitnumber", bitnumber);
            object.put("parameterid", parameterid);
            object.put("paramdescription", paramdescription);


            List<I4AllSetting> datablocks = db.getitembyitesref(600);

            int dbid = 0;
            for (I4AllSetting blocks : datablocks) {
                JSONObject tmp = new JSONObject(blocks.getItemsData());
                if (tmp.getString("name").equals(main.binding.spplc.getSelectedItem().toString())) {// on select datablock
                    dbid = tmp.getInt("id");
                    break;
                }
            }
            if (dbid == 0) {
                Toast.makeText(main, "Error in read DataBlockId", Toast.LENGTH_SHORT).show();
                return;
            }


            List<I4AllSetting> data = db.getitembyitesref(630);

            for (I4AllSetting item : data) {
                JSONObject tag = new JSONObject(item.getItemsData());
                if (tag.getInt("tagid") == Integer.parseInt(tagid)){// its need to update
                    item.setItemsData(object.toString());
                    db.update(item);
                    exit();
                    return;
                }
            }

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            I4AllSetting newdata = new I4AllSetting(0, 630, object.toString(), false, timeStamp);
            db.insert(newdata);


//        int plcid=0;
//        for (I4AllSetting item:spdata){
//            try {
//                JSONObject temp = new JSONObject(item.getItemsData());
//                if (temp.getString("datablockparameter").equals(datablockparameter)){
//                    plcid = temp.getInt("deviceid");
//                    break;
//                }
//            } catch (JSONException e) {
//                Toast.makeText(main, "db error in read plc id", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        if (plcid==0){
//            Toast.makeText(main, "Error In Read PLC ID", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        List<I4AllSetting> data = db.getitembyitesref(plcid);
//        if (data.size()==0){ // force insert
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            I4AllSetting newdata = new I4AllSetting(0, plcid, object.toString(), false, timeStamp);
//            db.insert(newdata);
//            exit();
//            return;
//        }
//        for (I4AllSetting items:data){
//            try {
//                JSONObject itobjext = new JSONObject(items.getItemsData());
//                if (itobjext.getString("tagid").equals(tagid)){//update
//                    //TODO REPEAT DATA
//                    items.setItemsData(object.toString());
//                    db.update(items);
//                    exit();
//                    return;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            I4AllSetting newdata = new I4AllSetting(0, plcid, object.toString(), false, timeStamp);
//            db.insert(newdata);
//        if (data==null){
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            data = new I4AllSetting(0,plcid,object.toString(),false,timeStamp);
//            db.insert(data);
//        }else{ // update data
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            data.setDateTime(timeStamp);
//            data.setItemsData(object.toString());
//            db.update(data);
//        }
            exit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

}
