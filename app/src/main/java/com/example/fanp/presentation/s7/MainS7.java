package com.example.fanp.presentation.s7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMainS7Binding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.tcp.MainModeBusTCPViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainS7 extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    @Inject
    I4AllSettingDao db;

    ActivityMainS7Binding binding;
    MainS7ViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_s7);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_s7);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MainS7ViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;


    }

    public void validationdata() {
        List<I4AllSetting> spdata = db.getplc();
        List<String> plcs = new ArrayList<>();
        for (I4AllSetting item : spdata) {
            try {
                JSONObject object = new JSONObject(item.getItemsData());
                plcs.add(object.getString("devicename"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (plcs.size() == 0) {
            binding.tagmanagment.setEnabled(false);
        } else
            binding.tagmanagment.setEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        validationdata();
    }
}