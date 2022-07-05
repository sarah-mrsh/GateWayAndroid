package com.example.fanp.presentation.modbus.rtu.serverlistrtu;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityServerListRtuBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.rtu.addserver.AddServerRtu;
import com.example.fanp.presentation.modbus.rtu.taglist.TagListRtu;
import com.example.fanp.presentation.modbus.tcp.serverlist.ServerListAdaptertCP;

import org.json.JSONArray;
import org.json.JSONException;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ServerListRtu extends DaggerAppCompatActivity implements ServerImp {


    ActivityServerListRtuBinding binding;

    ServerListRTUViewModel viewmodel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    I4AllSettingDao db;


    AdapterServerRtu adapter;


    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_server_list_rtu);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(ServerListRTUViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;
        setContentView(binding.getRoot());

        I4AllSetting data = db.getrtumodbusserverlist();


    }

    public void refresh() {
        I4AllSetting data = db.getrtumodbusserverlist();
        if (data != null) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            adapter = new AdapterServerRtu(data, this, this);
            binding.recrtu.setLayoutManager(manager);
            binding.recrtu.setAdapter(adapter);
        }
    }


    @Override
    public void edit(I4AllSetting item, int index) {
        AddServerRtu.update=true;
        AddServerRtu.index=index;
        AddServerRtu.item=item;
        startActivity(new Intent(this,AddServerRtu.class));
    }

    @Override
    public void delete(I4AllSetting item, int index) {
        try {
            JSONArray data = new JSONArray(item.getItemsData());
            data.remove(index);
            item.setItemsData(data.toString());
            db.update(item);
            refresh();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void taglist(I4AllSetting item, int index) {
        TagListRtu.index=index;
        TagListRtu.item=item;

        startActivity(new Intent(this, TagListRtu.class));
    }
}