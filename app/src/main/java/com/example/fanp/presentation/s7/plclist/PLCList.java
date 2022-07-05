package com.example.fanp.presentation.s7.plclist;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityPlclistBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.rtu.serverlistrtu.AdapterServerRtu;
import com.example.fanp.presentation.s7.addplc.AddPlc;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class PLCList extends DaggerAppCompatActivity implements PlcListImp {

    ActivityPlclistBinding binding;
    PLCListViewModel viewmodel;


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    @Inject
    I4AllSettingDao db;


    AdapterPlcList adapter;


    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_plclist);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(PLCListViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;


    }

    public void refresh() {
        List<I4AllSetting> data = db.getplc();
        if (data != null) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            adapter = new AdapterPlcList(this, this, data);
            binding.recplc.setLayoutManager(manager);
            binding.recplc.setAdapter(adapter);
        }
    }

    @Override
    public void edit(I4AllSetting item) {
        AddPlc.update = true;
        AddPlc.item = item;
        startActivity(new Intent(this, AddPlc.class));

    }


    public boolean is_have_tag(int id) {
        List<I4AllSetting> data = db.getitembyitesref(id);
        if (data.size() != 0)
            return true;
        else
            return false;

    }

    @Override
    public void delete(I4AllSetting item) {

        try {
            JSONObject object = new JSONObject(item.getItemsData());
            int deviceid =Integer.parseInt(object.getString("deviceid"));
            if (!is_have_tag(deviceid)) {
                db.delete(item);
            } else
                Toast.makeText(xcs, getResources().getString(R.string.deleteunavailable), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }




        refresh();


    }
}