package com.example.fanp.presentation.modbus.tcp.tag;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddTagBinding;
import com.example.fanp.databinding.ActivityAddTagTcpBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddTagTCP extends DaggerAppCompatActivity {



    public static I4AllSetting item;
    public static int index;

    ActivityAddTagTcpBinding binding;
    AddTagTCPViewModel viewmodel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    I4AllSettingDao db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tag_tcp);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddTagTCPViewModel.class);
        binding.setViewmodel(viewmodel);
        setContentView(binding.getRoot());
        viewmodel.main=this;
    }
}