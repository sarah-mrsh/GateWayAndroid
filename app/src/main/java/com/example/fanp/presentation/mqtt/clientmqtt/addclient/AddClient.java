package com.example.fanp.presentation.mqtt.clientmqtt.addclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.EditText;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddClientBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.tcp.MainModeBusTCPViewModel;
import com.example.fanp.utils.Toaster;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddClient extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    I4AllSettingDao db;

    ActivityAddClientBinding binding;
    AddClientViewModel viewmodel;

    public static I4AllSetting ddbdata;
    public static boolean update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_client);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddClientViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;

        List<EditText> list = new ArrayList<>();
        list.add(binding.edtip);
        list.add(binding.edtclientid);

        Toaster<?> tt = new Toaster<>(list);


        if (update) {
            binding.edtclientid.setEnabled(false);
            if (ddbdata != null)
                try {
                    JSONObject data = new JSONObject(ddbdata.getItemsData());
                    if (data.has("clientname")) {
                        viewmodel.clientname = (data.getString("clientname"));
                    }
                    if (data.has("username")) {
                        viewmodel.username = (data.getString("username"));
                    }
                    if (data.has("protocol")) {
                        viewmodel.protocol = (data.getString("protocol"));
                    }
                    if (data.has("reconnect")) {
                        viewmodel.reconnect = (data.getString("reconnect"));
                    }
                    if (data.has("qos")) {
                        viewmodel.qos = (data.getString("qos"));
                    }
                    if (data.has("willtopic")) {
                        viewmodel.willtopic = (data.getString("willtopic"));
                    }
                    if (data.has("clientid")) {
                        viewmodel.clientid = (data.getString("clientid"));
                    }
                    if (data.has("ip")) {
                        viewmodel.ip = (data.getString("ip"));
                    }
                    if (data.has("port")) {
                        viewmodel.port = (data.getString("port"));
                    }
                    if (data.has("password")) {
                        viewmodel.password = (data.getString("password"));
                    }
                    if (data.has("timeout")) {
                        viewmodel.timeout = (data.getString("timeout"));
                    }
                    if (data.has("sessiontime")) {
                        viewmodel.sessiontime = (data.getString("sessiontime"));
                    }
                    if (data.has("sendtimestamp")) {
                        viewmodel.sendtimestamp = (data.getBoolean("sendtimestamp"));
                    }
                    if (data.has("keepalive")) {
                        viewmodel.keepalive = (data.getBoolean("keepalive"));
                    }
                    if (data.has("compatibleversion")) {
                        viewmodel.compatibleversion = (data.getBoolean("compatibleversion"));
                    }
                    if (data.has("maintainewill")) {
                        viewmodel.maintainewill = (data.getBoolean("maintainewill"));
                    }
                    if (data.has("willcardsub")) {
                        viewmodel.willcardsub = (data.getBoolean("willcardsub"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            update = false;
        }
    }
}