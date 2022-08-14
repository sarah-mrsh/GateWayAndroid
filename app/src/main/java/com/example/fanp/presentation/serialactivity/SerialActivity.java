package com.example.fanp.presentation.serialactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivitySerialBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.presentation.s7.MainS7ViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.grpc.ManagedChannel;

public class SerialActivity extends DaggerAppCompatActivity {

    @Inject
    ManagedChannel channel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Context xcs;

    @Inject
    I4AllSettingDao db;

    ActivitySerialBinding binding;

    SerialViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_serial);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(SerialViewModel.class);
        binding.setViewmodel(viewmodel);

    }
}