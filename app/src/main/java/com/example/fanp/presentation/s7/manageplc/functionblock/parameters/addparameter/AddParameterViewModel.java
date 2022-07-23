package com.example.fanp.presentation.s7.manageplc.functionblock.parameters.addparameter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.manageplc.functionblock.parameters.AdapterPrameterFunctionBloc;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddParameterViewModel extends ViewModel {

    @Inject
    public AddParameterViewModel() {
    }


    public String name;
    public String type;
    public String address;
    public String bitnumber;
    public String parameterid;
    public String description;

    public AddParameterFunctionBlock main;

    @Inject
    I4AllSettingDao db;


    @Inject
    Context xcs;





    public void onSelectType(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        type = parent.getAdapter().getItem(pos).toString();
    }


    public void savedata() {


        JSONObject object = new JSONObject();
        try {
            object.put("name", name);
            object.put("parameterid", parameterid);
            object.put("type", type);
            object.put("address", address);
            object.put("bitnumber", bitnumber);
            object.put("description", description);


            JSONObject parentitem = new JSONObject(AddParameterFunctionBlock.item.getItemsData());

            List<I4AllSetting> spdata = db.getitembyitesref(parentitem.getInt("functionblocknumber"));
            I4AllSetting data = null;
            int plcid = 0;
            for (I4AllSetting item : spdata) {
                    JSONObject temp = new JSONObject(item.getItemsData());
                    if (temp.getString("parameterid").equals(parameterid)) {
                        plcid = temp.getInt("parameterid");
                        data = item;
                        break;
                    }
            }

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            if (plcid!=0){//need to update
                data.setItemsData(object.toString());
                data.setDateTime(timeStamp);
                db.update(data);
            }else{// it is new
                data = new I4AllSetting(0, parentitem.getInt("functionblocknumber"), object.toString(), false, timeStamp);
                db.insert(data);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void exit() {
        main.finish();
    }

}
