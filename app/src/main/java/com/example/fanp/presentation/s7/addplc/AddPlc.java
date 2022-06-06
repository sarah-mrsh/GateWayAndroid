package com.example.fanp.presentation.s7.addplc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddPlcBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.manageplc.ManagePLCViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddPlc extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    ActivityAddPlcBinding binding;
    AddPlcViewModel viewmodel;

    public static boolean update=false;
    public static I4AllSetting item;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_plc);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddPlcViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;

        if (update){
            binding.deviceid.setEnabled(false);
            try {
                JSONObject object = new JSONObject(item.getItemsData());
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