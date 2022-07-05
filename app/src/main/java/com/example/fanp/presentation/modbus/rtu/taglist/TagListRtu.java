package com.example.fanp.presentation.modbus.rtu.taglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityTagListBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.rtu.serverlistrtu.ServerListRTUViewModel;
import com.example.fanp.presentation.modbus.rtu.tag.AddTag;
import com.example.fanp.presentation.modbus.tcp.serverlist.ServerListAdaptertCP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TagListRtu extends DaggerAppCompatActivity {


    ActivityTagListBinding binding;
    TagListViewModel viewmodel;

    @Inject
    I4AllSettingDao db;

    @Inject
    ViewModelProviderFactory providerFactory;


    public static I4AllSetting item;
    public static int index;


    TagListRtuViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_list);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tag_list);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(TagListViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {

        JSONArray array = null;
        try {
            array = new JSONArray(TagListRtu.item.getItemsData());
            JSONObject jsonObject = array.getJSONObject(TagListRtu.index);
            int deviceid = jsonObject.getInt("deviceid");
            List<I4AllSetting> data = db.getitembyitesref(deviceid);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            adapter = new TagListRtuViewAdapter(data, this);
            binding.recTaglist.setLayoutManager(manager);
            binding.recTaglist.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}