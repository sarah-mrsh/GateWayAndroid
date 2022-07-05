package com.example.fanp.presentation.mqtt.clientmqtt.addtag;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.TagListMqttClient;
import com.example.fanp.utils.IdEdt;
import com.example.fanp.utils.IpEdt;
import com.example.fanp.utils.NameEdt;
import com.example.fanp.utils.PortEdt;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddTagMqttViewModel extends ViewModel {



    AddTagMqttClient main;

    @Inject
    Context ctx;

    @Inject
    I4AllSettingDao db;


    public String tagname;
    public String tagid;
    public String type;
    public String topicname;



    @Inject
    public AddTagMqttViewModel() {

    }


    public void validation(IdEdt id, NameEdt name){

        if (!name.valid){
            Toast.makeText(ctx, "Name is not valid.", Toast.LENGTH_SHORT).show();
            return ;

        }

        if (!id.valid){
            Toast.makeText(ctx, "ID is not valid.", Toast.LENGTH_SHORT).show();
            return ;
        }

        savedata();
    }


    public void savedata() {

        JSONObject object = new JSONObject();
        try {
            object.put("tagname", tagname);
            object.put("tagid", tagid);
            object.put("type", type);
            object.put("topicname", topicname);


            JSONObject tmp = new JSONObject(TagListMqttClient.item.getItemsData());


            List<I4AllSetting> data = db.getitembyitesref(Integer.parseInt(tmp.getString("clientid")));

            for (int i = 0; i < data.size(); i++) {

                JSONObject object1 = new JSONObject(data.get(i).getItemsData());
                if (object1.getString("tagid").equals(tagid)) {//update
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                    data.get(i).setDateTime(timeStamp);
                    data.get(i).setItemsData(object.toString());
                    db.update(data.get(i));
                    main.finish();
                    return;
                }

            }


            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            I4AllSetting ndata = new I4AllSetting(0, Integer.parseInt(tmp.getString("clientid")), object.toString(), false, timeStamp);
            db.insert(ndata);

            main.finish();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void exit(){
        main.finish();
    }
    public void onSelectItempdatattype(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        type = parent.getAdapter().getItem(pos).toString();
    }


}
