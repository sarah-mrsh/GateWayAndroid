package com.example.fanp.presentation.node.add;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddNodeBinding;
import com.example.fanp.databinding.ActivityAddTagMqttClientBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.convertprotocol.AdapterConvertProtol;
import com.example.fanp.presentation.mqtt.clientmqtt.addtag.AddTagMqttViewModel;

import org.checkerframework.checker.units.qual.A;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddNode extends DaggerAppCompatActivity implements tagImp {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    I4AllSettingDao db;

    ActivityAddNodeBinding binding;
    AddNodeViewModel viewmodel;

    public static I4AllSetting ddbdata;
    public static boolean update;


    AdapterTags adapter;
    List<NodeTagModel> list = new ArrayList<>();// list of final data filled by user
    List<SpinnerModel> data = new ArrayList<>(); // list of spinner data
    List<String> tags = new ArrayList<>();//list of spinner tags

    String selected = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_node);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddNodeViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;


        try {
            fill_spinner_destination();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        binding.spdest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getAdapter().getItem(position).toString();
                viewmodel.destination = selected;
                try {
                    fill_tags();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void fill_tags() throws JSONException {
        tags.clear();
        list.clear();//remove all recyclerview row
        refresh();

        List<I4AllSetting> converts = db.getconvertprotocols();


        for (SpinnerModel item : data) {
            if (selected.equals(item.name)) {
                viewmodel.type = item.type;// Fill type in db
                switch (item.type) {
                    case "PLC":
                        break;
                    case "MQTT": {
                        for (I4AllSetting obj : item.tags) {
                            for (I4AllSetting convert : converts) {
                                if (new JSONObject(obj.getItemsData()).getString("tagname").equals(new JSONObject(convert.getItemsData()).getString("to"))){
                                    tags.add(new JSONObject(obj.getItemsData()).getString("tagname"));
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "TCP": {
                        for (I4AllSetting obj : item.tags) {
                            tags.add(new JSONObject(obj.getItemsData()).getString("name"));
                        }
                        break;
                    }
                    case "RTU": {
                        for (I4AllSetting obj : item.tags) {
                            tags.add(new JSONObject(obj.getItemsData()).getString("name"));
                        }
                        break;
                    }
                }
            }
        }
        list.add(new NodeTagModel("", "", ""));
        refresh();
    }

    public void fill_spinner_destination() throws JSONException {
        List<I4AllSetting> plcs = db.getplc();
        List<I4AllSetting> mqtt = db.getmqttclient();
        List<I4AllSetting> tcp = db.getitembyitesref(505);
        List<I4AllSetting> rtumaster = db.getitembyitesref(512);
        List<I4AllSetting> rtuslave = db.getitembyitesref(513);


        data.addAll(i42string(plcs, 0));
        data.addAll(i42string(mqtt, 1));
        data.addAll(i42string(tcp, 2));
        data.addAll(i42string(rtumaster, 3));

        List<String> tmp = new ArrayList<>();
        for (SpinnerModel item : data) {
            tmp.add(item.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tmp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spdest.setAdapter(adapter);

    }

    public List<SpinnerModel> i42string(List<I4AllSetting> free, int type) throws JSONException {
        List<SpinnerModel> data = new ArrayList<>();

        switch (type) {
            case 0: {//PLC
//                for (I4AllSetting item : free) {
//                    JSONObject object = new JSONObject(item.getItemsData());
//                    SpinnerModel model = new SpinnerModel(object.getString("devicename"),"PLC",object.getString("deviceid"),null);
//                    data.add(model);
//                }
                break;
            }
            case 1: {//MQTT
                for (I4AllSetting item : free) {
                    JSONObject object = new JSONObject(item.getItemsData());
                    List<I4AllSetting> tags = db.getitembyitesref(Integer.parseInt(object.getString("clientid")));
                    SpinnerModel model = new SpinnerModel(object.getString("clientname"), "MQTT", object.getString("clientid"), tags);
                    data.add(model);
                }
                break;
            }
            case 2: {//TCP
//                for (I4AllSetting item : free) {
//                    JSONObject object = new JSONObject(item.getItemsData());
//                    List<I4AllSetting> tags = db.getitembyitesref(object.getInt("deviceid"));
//                    SpinnerModel model = new SpinnerModel(object.getString("devicename"),"TCP",object.getString("deviceid"),tags);
//                    data.add(model);
//                }
                break;
            }
            case 3: {//RTU
//                for (I4AllSetting item : free) {
//                    JSONObject object = new JSONObject(item.getItemsData());
//                    List<I4AllSetting> tags = db.getitembyitesref(object.getInt("deviceid"));
//                    SpinnerModel model = new SpinnerModel(object.getString("devicename"),"RTU",object.getString("deviceid"),tags);
//                    data.add(model);
//                }
                break;
            }
            default: {
                return data;
            }
        }
        return data;
    }


    public void refresh() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new AdapterTags(list, this, this, tags);
        binding.recTags.setLayoutManager(manager);
        binding.recTags.setAdapter(adapter);
    }

    @Override
    public void temp_save() {
        list.clear();
        for (int i = 0; i < binding.recTags.getChildCount(); i++) {
            AdapterTags.ViewHolder holder = (AdapterTags.ViewHolder) binding.recTags.findViewHolderForAdapterPosition(i);
            assert holder != null;
            list.add(new NodeTagModel(holder.edtname.getText().toString(), holder.sptag.getSelectedItem().toString(), holder.spmode.getSelectedItem().toString()));
        }
        refresh();
    }

    @Override
    public void tmep_save_remove() {
        list.clear();
        for (int i = 0; i < binding.recTags.getChildCount() - 1; i++) {
            AdapterTags.ViewHolder holder = (AdapterTags.ViewHolder) binding.recTags.findViewHolderForAdapterPosition(i);
            assert holder != null;
            list.add(new NodeTagModel(holder.edtname.getText().toString(), holder.sptag.getSelectedItem().toString(), holder.spmode.getSelectedItem().toString()));
        }
        refresh();
    }


}