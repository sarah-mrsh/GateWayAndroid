package com.example.fanp.presentation.modbus.tcp.addserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddServertcpBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.repository.I4AllSetting;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddServertcp extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;

    ActivityAddServertcpBinding binding;
    AddServeTcpViewModel viewmodel;

    public static boolean update=false;
    public static I4AllSetting item;
    public static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_servertcp);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddServeTcpViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;
        if (update){
            binding.edtid.setEnabled(false);
            try {
                JSONArray data = new JSONArray(item.getItemsData());
                JSONObject object = data.getJSONObject(index);
                if (object.has("devicename")){viewmodel.devicename=object.getString("devicename");}
                if (object.has("deviceid")){viewmodel.deviceid=object.getString("deviceid");}
                if (object.has("ip")){viewmodel.ip=object.getString("ip");}
                if (object.has("port")){viewmodel.port=object.getString("port");}
            } catch (JSONException e) {
                e.printStackTrace();
            }
            update=false;
        }

    }
}