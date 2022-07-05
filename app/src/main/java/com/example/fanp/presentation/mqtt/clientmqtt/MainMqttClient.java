package com.example.fanp.presentation.mqtt.clientmqtt;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMainMqttClientBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.addclient.AddClient;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.TagListMqttClient;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.TagListMqttViweModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainMqttClient extends DaggerAppCompatActivity implements mqttClientImp {

    @Inject
    ViewModelProviderFactory providerFactory;


    ActivityMainMqttClientBinding binding;
    MainMqttViewModel viewmodel;

    MqttClientListAdapter adapter;

    @Inject
    I4AllSettingDao db;

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
        viewmodel.updatedata();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_mqtt_client);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MainMqttViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;

    }


    public void refresh() {
        List<I4AllSetting> list = db.getmqttclient();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new MqttClientListAdapter(this, list, this);
        binding.recserverlist.setLayoutManager(manager);
        binding.recserverlist.setAdapter(adapter);


    }


    @Override
    public void update(I4AllSetting item) {
        AddClient.update = true;
        AddClient.ddbdata = item;
        startActivity(new Intent(this, AddClient.class));
    }

    @Override
    public void delete(I4AllSetting item) {
        db.delete(item);
        refresh();
    }

    @Override
    public void taglist(I4AllSetting item) {
        TagListMqttClient.item = item;
        startActivity(new Intent(this, TagListMqttClient.class));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}