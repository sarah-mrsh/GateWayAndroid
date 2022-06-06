package com.example.fanp.presentation.modbus.tcp.taglisttcp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityTaglistTcpBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.rtu.taglist.TagListRtu;
import com.example.fanp.presentation.modbus.rtu.taglist.TagListRtuViewAdapter;
import com.example.fanp.presentation.modbus.rtu.taglist.TagListViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TaglistTcp extends DaggerAppCompatActivity {



    ActivityTaglistTcpBinding binding;
    TagListTcpViewModel viewmodel;


    TagListTCPViewAdapter adapter;

    @Inject
    I4AllSettingDao db;

    @Inject
    ViewModelProviderFactory providerFactory;


    public static I4AllSetting item;
    public static int index;


    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_taglist_tcp);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(TagListTcpViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;

        refresh();


    }

    public void refresh() {
        JSONArray array = null;
        try {
            array = new JSONArray(TaglistTcp.item.getItemsData());
            JSONObject jsonObject = array.getJSONObject(TaglistTcp.index);
            int deviceid = jsonObject.getInt("deviceid");
            List<I4AllSetting> data = db.getitembyitesref(deviceid);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            adapter = new TagListTCPViewAdapter(data, this);
            binding.recTaglist.setLayoutManager(manager);
            binding.recTaglist.setAdapter(adapter);
        } catch (JSONException e) {
            Toast.makeText(this, "error in device ID", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


}