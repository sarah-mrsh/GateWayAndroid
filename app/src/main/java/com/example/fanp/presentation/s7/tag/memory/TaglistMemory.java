package com.example.fanp.presentation.s7.tag.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.di.injector.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TaglistMemory extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taglist_memory);
    }
}