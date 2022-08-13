package com.example.fanp.presentation.business;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMainBusinessBinding;
import com.example.fanp.databinding.ActivityMainMqttClientBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.business.add.AddBusiness;
import com.example.fanp.presentation.mqtt.clientmqtt.MainMqttViewModel;
import com.example.fanp.presentation.mqtt.clientmqtt.MqttClientListAdapter;
import com.example.fanp.presentation.mqtt.clientmqtt.addclient.AddClient;
import com.example.fanp.presentation.mqtt.clientmqtt.mqttClientImp;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.TagListMqttClient;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainBusiness extends DaggerAppCompatActivity implements BusinessListImpl {

    @Inject
    ViewModelProviderFactory providerFactory;


    ActivityMainBusinessBinding binding;
    MainBusinessViewModel viewmodel;

    AdapterBusinessList adapter;

    @Inject
    I4AllSettingDao db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_business);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MainBusinessViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;



    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        List<I4AllSetting> list = db.get_businesses();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new AdapterBusinessList(this, list,this);
        binding.recserverlist.setLayoutManager(manager);
        binding.recserverlist.setAdapter(adapter);
    }


    @Override
    public void delete(I4AllSetting item) {
        db.delete(item);
        refresh();
    }

    @Override
    public void edit(I4AllSetting item) {
        AddBusiness.ddbdata=item;
        AddBusiness.update=true;
        startActivity(new Intent(this,AddBusiness.class));
    }
}