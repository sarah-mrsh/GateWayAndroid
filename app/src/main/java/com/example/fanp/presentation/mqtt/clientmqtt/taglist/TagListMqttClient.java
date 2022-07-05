package com.example.fanp.presentation.mqtt.clientmqtt.taglist;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityTagListMqttClientBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.addtag.AddTagMqttClient;
import com.example.fanp.presentation.wifi.savednetworks.SavedWifiAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TagListMqttClient extends DaggerAppCompatActivity implements MqttTaglistImp {

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    I4AllSettingDao db;

    ActivityTagListMqttClientBinding binding;
    TagListMqttViweModel viewmodel;


    public static I4AllSetting item;

    AdapterTagListMqttTags adapter;

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_list_mqtt_client);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tag_list_mqtt_client);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(TagListMqttViweModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;


    }

    public void refresh() {
        try {
            JSONObject object = new JSONObject(item.getItemsData());

            List<I4AllSetting> data = db.getitembyitesref(Integer.parseInt(object.getString("clientid")));

            adapter = new AdapterTagListMqttTags(data, this, this);
            binding.recTaglist.setAdapter(adapter);
            binding.recTaglist.setLayoutManager(new LinearLayoutManager(this));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(I4AllSetting item) {
        db.delete(item);
        refresh();
    }

    @Override
    public void update(I4AllSetting item) {
        AddTagMqttClient.update=true;
        AddTagMqttClient.ddbdata=item;
        startActivity(new Intent(this,AddTagMqttClient.class));
    }
}