package com.example.fanp.presentation.modbus.rtu.addserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddServerRtuBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.rtu.serverlistrtu.ServerListRTUViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddServerRtu extends DaggerAppCompatActivity {



    ActivityAddServerRtuBinding binding;
    AddServerViewModel viewmodel;

    public static boolean update=false;
    public static I4AllSetting item;
    public static int index;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    I4AllSettingDao db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_server_rtu);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddServerViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;
        if (update){
            binding.deviceid.setEnabled(false);
            try {
                JSONArray data = new JSONArray(item.getItemsData());
                JSONObject object = data.getJSONObject(index);
                if (object.has("devicename")){viewmodel.devicename=object.getString("devicename");}
                if (object.has("deviceid")){viewmodel.deviceid=object.getString("deviceid");}
                if (object.has("modbusadress")){viewmodel.modbusadress=object.getString("modbusadress");}
            } catch (JSONException e) {
                e.printStackTrace();
            }
            update=false;
        }
    }
}