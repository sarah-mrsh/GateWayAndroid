package com.example.fanp.presentation.modbus.rtu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMainModBusRtuBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.tcp.MainModeBusTCPViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainModBusRTU extends DaggerAppCompatActivity {


    ActivityMainModBusRtuBinding binding;
    MainModbudRtuViewModel viewmodel;


    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    I4AllSettingDao db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_mod_bus_rtu);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MainModbudRtuViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;





        I4AllSetting data = db.getitembyId(512);
        if (data!=null){


            try {
                JSONObject object  =new JSONObject(data.getItemsData());
                if (object.has("devicename")){viewmodel.devicename = object.getString("devicename");}
                if (object.has("deviceid")){viewmodel.deviceid = object.getString("deviceid");}
                if (object.has("baudrate")){viewmodel.baudrate = object.getString("baudrate");}
                if (object.has("startbit")){viewmodel.startbit = object.getString("startbit");}
                if (object.has("databit")){viewmodel.databit = object.getString("databit");}
                if (object.has("parity")){viewmodel.parity = object.getString("parity");}
                if (object.has("endbit")){viewmodel.endbit = object.getString("endbit");}
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}