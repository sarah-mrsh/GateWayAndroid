package com.example.fanp.presentation.s7.tag.io;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityTagListIoBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.tag.datablock.AdapterDatablock;
import com.example.fanp.presentation.s7.tag.datablock.AddDatatBlockTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TagListIo extends DaggerAppCompatActivity implements  ioImp{


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;

    @Inject
    I4AllSettingDao db;


    ActivityTagListIoBinding binding;
    TagListIOViewModel viewmodel;

    AdapterIOtaglist adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_tag_list_io);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(TagListIOViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;

        refresh();

    }

    public void refresh(){
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
            for (I4AllSetting dataitem:items){
                try {
                    JSONObject object=new JSONObject(dataitem.getItemsData());
                    if (object.has("iotype")){
                        data.add(dataitem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }



            LinearLayoutManager manager = new LinearLayoutManager(this);
            adapter = new AdapterIOtaglist(this,this,data);
            binding.recio.setLayoutManager(manager);
            binding.recio.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void delete(I4AllSetting item) {
        db.delete(item);
        refresh();
    }

    @Override
    public void edit(I4AllSetting item) {
        AddTagIo.item=item;
        AddTagIo.update=true;
        startActivity(new Intent(this,AddTagIo.class));
    }
}