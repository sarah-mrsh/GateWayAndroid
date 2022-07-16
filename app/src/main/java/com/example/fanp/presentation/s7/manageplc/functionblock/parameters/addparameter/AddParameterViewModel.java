package com.example.fanp.presentation.s7.manageplc.functionblock.parameters.addparameter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.manageplc.functionblock.parameters.AdapterPrameterFunctionBloc;
import com.example.fanp.utils.NameEdt;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddParameterViewModel extends ViewModel {


    public String name;
    public String type;
    public String address;
    public String bitnumber;
    public String description;

    public AddParameterFunctionBlock main;

    @Inject
    Context ctx;

    @Inject
    public AddParameterViewModel() {
    }

    public void validation(NameEdt name){

        if(!name.valid){

            Toast.makeText(ctx, "Name is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }
        savedata();

    }

    public void savedata() {

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




//    public void savedata() {
//        JSONObject object = new JSONObject();
//        try {
//            object.put("name", name);
//            object.put("plcname", plcname);
//            object.put("id", id);
//            object.put("functionblock", functionblock);
//            object.put("description", description);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        List<I4AllSetting> spdata = db.getplc();
//        int plcid = 0;
//        for (I4AllSetting item : spdata) {
//            try {
//                JSONObject temp = new JSONObject(item.getItemsData());
//                if (temp.getString("devicename").equals(plcname)) {
//                    plcid = temp.getInt("deviceid");
//                    break;
//                }
//            } catch (JSONException e) {
//                Toast.makeText(ctx, "db error in read plc id", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        List<I4AllSetting> datas = db.getplcdatablocks();
//        for (I4AllSetting item : datas){
//            try {
//                JSONObject object1 = new JSONObject(item.getItemsData());
//                if (object1.getString("id").equals(id)){//need to update
//                    item.setItemsData(object.toString());
//                    db.update(item);
//                    main.finish();
//                    return;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
////        I4AllSetting data = db.getitembyId(plcid);
//        I4AllSetting data;
////        if (data==null){
//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//        data = new I4AllSetting(0, 600, object.toString(), false, timeStamp);
//        db.insert(data);
////        }else{ // update data
////            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
////            data.setDateTime(timeStamp);
////            data.setItemsData(object.toString());
////            db.update(data);
////        }
//        main.finish();
//    }



    public void exit() {
        main.finish();
    }

}
