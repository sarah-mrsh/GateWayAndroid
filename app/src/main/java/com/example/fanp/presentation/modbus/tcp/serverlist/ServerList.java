package com.example.fanp.presentation.modbus.tcp.serverlist;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityServerListBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.tcp.addserver.AddServertcp;
import com.example.fanp.presentation.modbus.tcp.taglisttcp.TaglistTcp;

import org.json.JSONArray;
import org.json.JSONException;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ServerList extends DaggerAppCompatActivity implements ServerTcpIml{


    ActivityServerListBinding binding;
    ServerLisViewMod viewmodel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    I4AllSettingDao db;

    ServerListAdaptertCP adapter;

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_server_list);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(ServerLisViewMod.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;
        setContentView(binding.getRoot());
        refresh();
    }

    public void refresh() {
        I4AllSetting data = db.gettcpmodbus();
        try {
            if (data != null) {
                JSONArray array = new JSONArray(data.getItemsData());
                LinearLayoutManager manager = new LinearLayoutManager(this);
                adapter = new ServerListAdaptertCP(array,this,data,this);
                binding.recserverlist.setLayoutManager(manager);
                binding.recserverlist.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void edit(I4AllSetting item, int index) {
        AddServertcp.update=true;
        AddServertcp.index=index;
        AddServertcp.item=item;
        startActivity(new Intent(this,AddServertcp.class));
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
        if (item!=null){
            TaglistTcp.index=index;
            TaglistTcp.item=item;
            startActivity(new Intent(this, TaglistTcp.class));
        }else{
            Toast.makeText(this, "error db", Toast.LENGTH_SHORT).show();
        }

    }
}