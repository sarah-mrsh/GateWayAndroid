package com.example.fanp.presentation.s7.manageplc.functionblock.parameters.addparameter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddParameterFunctionBlockBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.presentation.s7.manageplc.functionblock.addfunctionblock.AddFunctionBlockPlcViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddParameterFunctionBlock extends DaggerAppCompatActivity {





    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    @Inject
    I4AllSettingDao db;


    public ActivityAddParameterFunctionBlockBinding binding;
    public  AddParameterViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parameter_function_block);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_parameter_function_block);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddParameterViewModel.class);
        binding.setViewmodel(viewmodel);

    }






}