package com.example.fanp.presentation.node.add;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.tcp.tag.AddTagTCP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddNodeViewModel extends ViewModel {


    public String name;
    public String nodeid;
    public String intervaltime;
    public String topic;
    public String type;
    public String destination;

    public AddNode main;


    @Inject
    public AddNodeViewModel() {
    }


    @Inject
    I4AllSettingDao db;


    public void savedata() {
        JSONObject object = new JSONObject();
        JSONArray data = new JSONArray();
        JSONArray tags = new JSONArray();
        try {
            object.put("name", name);
            object.put("nodeid", nodeid);
            object.put("intervaltime", intervaltime);
            object.put("topic", topic);
            object.put("type", type);
            object.put("destination", destination);
            for (int i = 0; i < main.binding.recTags.getChildCount(); i++) {
                AdapterTags.ViewHolder holder = (AdapterTags.ViewHolder) main.binding.recTags.findViewHolderForAdapterPosition(i);
                assert holder != null;
                JSONObject tag = new JSONObject();
                tag.put("name", holder.edtname.getText().toString());
                tag.put("tag", holder.sptag.getSelectedItem().toString());
                tag.put("mode", holder.spmode.getSelectedItem().toString());
                tags.put(tag);
            }
            data.put(object);
            data.put(tags);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        db.insert(new I4AllSetting(0, 805, data.toString(), false, timeStamp));

        main.finish();
    }


}
