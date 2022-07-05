package com.example.fanp.presentation.s7.tag.datablock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityTagListDatablockBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.rtu.serverlistrtu.AdapterServerRtu;
import com.example.fanp.presentation.s7.plclist.PLCListViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TagListDatablock extends DaggerAppCompatActivity implements DatablockImp {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;

    TagListDataBlockViewModel viewmodel;
    ActivityTagListDatablockBinding binding;

    @Inject
    I4AllSettingDao db;


    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    AdapterDatablock adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tag_list_datablock);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(TagListDataBlockViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;
    }


    public void refresh() {
        List<I4AllSetting> data = new ArrayList<>();
        List<I4AllSetting> spdata = db.getplc();
        List<String> plcs = new ArrayList<>();
        for (I4AllSetting item : spdata) {
            try {
                JSONObject object = new JSONObject(item.getItemsData());
                plcs.add(object.getString("deviceid"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for (String item : plcs) {
            List<I4AllSetting> items = db.getitembyitesref(Integer.parseInt(item));
            for (I4AllSetting dataitem : items) {
                try {
                    JSONObject object = new JSONObject(dataitem.getItemsData());
                    if (!object.has("iotype")) {
                        data.add(dataitem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new AdapterDatablock(this, this, data);
        binding.recdatablock.setLayoutManager(manager);
        binding.recdatablock.setAdapter(adapter);

    }

    @Override
    public void delete(I4AllSetting item) {
        db.delete(item);
        refresh();
    }

    @Override
    public void edit(I4AllSetting item) {
        AddDatatBlockTag.item = item;
        AddDatatBlockTag.update = true;
        startActivity(new Intent(this, AddDatatBlockTag.class));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}