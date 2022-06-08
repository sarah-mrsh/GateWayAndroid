package com.example.fanp.presentation.s7.manageplc.functionblock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityDataBlockPlcBinding;
import com.example.fanp.databinding.ActivityFunctionBlockPlcBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.manageplc.adddatablockplc.AddDataBlockPlc;
import com.example.fanp.presentation.s7.manageplc.datablockplc.AdapterDataBlockPlcList;
import com.example.fanp.presentation.s7.manageplc.datablockplc.DatablockplcViewModel;
import com.example.fanp.presentation.s7.manageplc.datablockplc.ListDatablockplcImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class FunctionBlockMainPlc extends DaggerAppCompatActivity implements ListDatablockplcImpl {

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    @Inject
    I4AllSettingDao db;


    ActivityFunctionBlockPlcBinding binding;
    DatablockplcViewModel viewmodel;


    public AdapterDataBlockPlcList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_function_block_plc);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(DatablockplcViewModel.class);
        binding.setViewmodel(viewmodel);



        binding.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddDataBlockPlc.class));
            }
        });


        binding.btncancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {

        List<I4AllSetting> spdata = db.getplcfunctionblocks();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new AdapterDataBlockPlcList(this, this, spdata);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);

    }

    @Override
    public void delete(I4AllSetting item) {
        db.delete(item);
        refresh();
    }

    @Override
    public void edit(I4AllSetting item) {
        AddDataBlockPlc.Update=true;
        AddDataBlockPlc.item=item;
        startActivity(new Intent(this,AddDataBlockPlc.class));
    }
}