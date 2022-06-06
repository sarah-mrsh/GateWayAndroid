package com.example.fanp.presentation.s7.tag.io;

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

public class AddTagIOViewmodel extends ViewModel {

    public  String tagname;
    public  String tagid;
    public  String plc;
    public  String tagtype;
    public  String iotype;
    public  String wordcount;
    public  String bytecount;
    public  String bitcount;
    public  String description;


    @Inject
    I4AllSettingDao db;


    AddTagIo main;

    @Inject
    public AddTagIOViewmodel(){}



    public void onSelectplc(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        plc = parent.getAdapter().getItem(pos).toString();
    }
    public void onSelecttagtype(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        tagtype = parent.getAdapter().getItem(pos).toString();

    }

    public void onSelectiotype(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        iotype = parent.getAdapter().getItem(pos).toString();
        if (iotype.equals("Analogue")){
            main.binding.linwordcount.setVisibility(View.VISIBLE);
            main.binding.linbit.setVisibility(View.GONE);
            main.binding.linByte.setVisibility(View.GONE);
        }else{
            main.binding.linbit.setVisibility(View.VISIBLE);
            main.binding.linByte.setVisibility(View.VISIBLE);
            main.binding.linwordcount.setVisibility(View.GONE);
        }
    }

    public void exit(){
        main.finish();
    }


//    public  String tagname;
//    public  String tagid;
//    public  String plc;
//    public  String tagtype;
//    public  String iotype;
//    public  String wordcount;
//    public  String bytecount;
//    public  String bitcount;
//    public  String description;



    public void savedata() {
        JSONObject object = new JSONObject();
        try {

            object.put("tagname", tagname);
            object.put("tagid", tagid);
            object.put("plc", plc);
            object.put("tagtype", tagtype);
            object.put("iotype", iotype);
            object.put("wordcount", wordcount);
            object.put("bytecount", bytecount);
            object.put("bitcount", bitcount);
            object.put("description", description);




        } catch (JSONException e) {
            e.printStackTrace();
        }



        List<I4AllSetting> spdata = db.getplc();
        int plcid=0;
        for (I4AllSetting item:spdata){
            try {
                JSONObject temp = new JSONObject(item.getItemsData());
                if (temp.getString("devicename").equals(plc)){
                    plcid = temp.getInt("deviceid");
                    break;
                }
            } catch (JSONException e) {
                Toast.makeText(main, "db error in read plc id", Toast.LENGTH_SHORT).show();
            }
        }

        if (plcid==0){
            Toast.makeText(main, "Error In Read PLC ID", Toast.LENGTH_SHORT).show();
            return;
        }
        List<I4AllSetting> data = db.getitembyitesref(plcid);
        if (data.size()==0){ // force insert
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            I4AllSetting newdata = new I4AllSetting(0, plcid, object.toString(), false, timeStamp);
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        I4AllSetting newdata = new I4AllSetting(0, plcid, object.toString(), false, timeStamp);
        db.insert(newdata);
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
    }

}
