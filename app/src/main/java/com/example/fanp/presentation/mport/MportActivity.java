package com.example.fanp.presentation.mport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMportBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.presentation.lan.LanViewModel;

import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MportActivity extends DaggerAppCompatActivity {


    ActivityMportBinding binding;
    MportViewModel viewmodel;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context ctx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = DataBindingUtil.setContentView(this, R.layout.activity_mport);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MportViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main=this;
        setContentView(binding.getRoot());
    }
}