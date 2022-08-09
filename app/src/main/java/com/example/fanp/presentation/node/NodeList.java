package com.example.fanp.presentation.node;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityNodeListBinding;
import com.example.fanp.databinding.ActivityTagListMqttClientBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.addtag.AddTagMqttClient;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.AdapterTagListMqttTags;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.MqttTaglistImp;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.TagListMqttViweModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NodeList extends DaggerAppCompatActivity {

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    I4AllSettingDao db;

    ActivityNodeListBinding binding;
    NodeListViewModel viewmodel;



    AdapterNodeList adapter;

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_list_mqtt_client);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_node_list);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(NodeListViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;



    }

    public void refresh() {

            List<I4AllSetting> data = db.getitembyitesref(805);

            adapter = new AdapterNodeList(data, this);
            binding.recTaglist.setAdapter(adapter);
            binding.recTaglist.setLayoutManager(new LinearLayoutManager(this));


    }


}