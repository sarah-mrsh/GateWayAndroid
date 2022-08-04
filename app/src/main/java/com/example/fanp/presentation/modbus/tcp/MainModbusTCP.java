package com.example.fanp.presentation.modbus.tcp;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMainModbusTcpBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.GrpcModBus;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainModbusTCP extends DaggerAppCompatActivity {

    ActivityMainModbusTcpBinding binding;
    MainModeBusTCPViewModel viewmodel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    I4AllSettingDao db;

    @Inject
    GrpcModBus grpcModBus;


    public boolean last_status=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_modbus_tcp);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MainModeBusTCPViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;

        I4AllSetting data = db.getitembyId(505);
        if (data != null) {
            enable_items(false);
            try {
                JSONObject object = new JSONObject(data.getItemsData());
                if (object.has("devicename")) {
                    viewmodel.devicename = object.getString("devicename");
                }
                if (object.has("deviceid")) {
                    viewmodel.deviceid = object.getString("deviceid");
                }
                if (object.has("ip")) {
                    viewmodel.ip = object.getString("ip");
                }
                if (object.has("port")) {
                    viewmodel.port = object.getString("port");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        grpcModBus.update_data();
    }

    public void enable_items(boolean status){
        last_status=status;
        if (status){
            binding.buttonProtocolCancelrequestText.setText(getResources().getString(R.string.save));
            binding.edtid.setEnabled(true);
            binding.edtip.setEnabled(true);
            binding.edtname.setEnabled(true);
            binding.edtport.setEnabled(true);
        }else{
            binding.buttonProtocolCancelrequestText.setText(getResources().getString(R.string.edit));
            binding.edtid.setEnabled(false);
            binding.edtip.setEnabled(false);
            binding.edtname.setEnabled(false);
            binding.edtport.setEnabled(false);
        }
    }
}