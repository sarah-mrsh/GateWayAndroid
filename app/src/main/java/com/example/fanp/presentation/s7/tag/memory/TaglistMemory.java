package com.example.fanp.presentation.s7.tag.memory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityTagListBinding;
import com.example.fanp.databinding.ActivityTaglistMemoryBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.plclist.AdapterPlcList;
import com.example.fanp.presentation.s7.plclist.PLCListViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TaglistMemory extends DaggerAppCompatActivity implements TagmemorylistImpl{


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;

    @Inject
    I4AllSettingDao db;

    public AdapterTagMemory adapter;
    public TagListMemoryVIewModel viewmodel;

    public ActivityTaglistMemoryBinding binding;

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taglist_memory);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_taglist_memory);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(TagListMemoryVIewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;
    }

    public void refresh() {
        List<I4AllSetting> data = db.getitembyitesref(603);
        if (data != null) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            adapter = new AdapterTagMemory(this, this, data);
            binding.recplc.setLayoutManager(manager);
            binding.recplc.setAdapter(adapter);
        }
    }

    @Override
    public void edit(I4AllSetting item) {
        AddTagMemory.update=true;
        AddTagMemory.data=item;
        startActivity(new Intent(this,AddTagMemory.class));
    }

    @Override
    public void delete(I4AllSetting item) {
        db.delete(item);
        refresh();
    }
}