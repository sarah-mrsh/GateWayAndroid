package com.example.fanp.presentation.s7.manageplc;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityManagePlcBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ManagePLC extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    I4AllSettingDao db;


    @Inject
    Context xcs;

    ActivityManagePlcBinding binding;
    ManagePLCViewModel viewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_plc);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_plc);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(ManagePLCViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;


    }

    @Override
    protected void onResume() {
        super.onResume();
        validationdata();
    }

    public void validationdata(){
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
            binding.txtfunctionclock.setEnabled(false);
            binding.txtdatablock.setEnabled(false);
        }else{
            binding.txtfunctionclock.setEnabled(true);
            binding.txtdatablock.setEnabled(true);
        }

    }


}