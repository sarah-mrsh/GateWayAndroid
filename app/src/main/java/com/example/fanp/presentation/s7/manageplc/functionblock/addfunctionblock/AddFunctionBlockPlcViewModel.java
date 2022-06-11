package com.example.fanp.presentation.s7.manageplc.functionblock.addfunctionblock;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.manageplc.adddatablockplc.AddDataBlockPlc;
import com.example.fanp.presentation.s7.manageplc.functionblock.parameters.addparameter.AddParameterFunctionBlock;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddFunctionBlockPlcViewModel extends ViewModel {



    @Inject
    public AddFunctionBlockPlcViewModel() {}

    public String functionblockname;
    public String functionblocknumber;
    public String plcname;
    public String description;


    @Inject
    I4AllSettingDao db;

    @Inject
    Context ctx;

    AddFunctionBlockPlc main;





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
            object.put("functionblockname", functionblockname);
            object.put("plcname", plcname);
            object.put("functionblocknumber", functionblocknumber);
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

        List<I4AllSetting> datas = db.getplcdatablocks();
        for (I4AllSetting item : datas){
            try {
                JSONObject object1 = new JSONObject(item.getItemsData());
                if (object1.getString("id").equals(functionblocknumber)){//need to update
                    item.setItemsData(object.toString());
                    db.update(item);
                    main.finish();
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        I4AllSetting data = db.getitembyId(plcid);
        I4AllSetting data;
//        if (data==null){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        data = new I4AllSetting(0, 601, object.toString(), false, timeStamp);
        db.insert(data);
//        }else{ // update data
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            data.setDateTime(timeStamp);
//            data.setItemsData(object.toString());
//            db.update(data);
//        }
        main.finish();
    }

    public void addparameter(){
        main.startActivity(new Intent(main, AddParameterFunctionBlock.class));
    }
}
