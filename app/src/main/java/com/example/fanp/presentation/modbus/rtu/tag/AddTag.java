package com.example.fanp.presentation.modbus.rtu.tag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddTagBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.rtu.addserver.AddServerViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddTag extends DaggerAppCompatActivity {



    public static I4AllSetting item;
    public static int index;

    ActivityAddTagBinding binding;
    AddTagViewModel viewmodel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    I4AllSettingDao db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tag);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddTagViewModel.class);
        binding.setViewmodel(viewmodel);
        setContentView(binding.getRoot());
        viewmodel.main=this;
    }
}