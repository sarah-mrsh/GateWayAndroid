package com.example.fanp.presentation.s7.plclist;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.s7.addplc.AddPlc;

import javax.inject.Inject;

public class PLCListViewModel extends ViewModel {


    PLCList main;


    @Inject
    public PLCListViewModel(){}

    public void exit(){
        main.finish();
    }

    public void addplc(){
        main.startActivity(new Intent(main, AddPlc.class));
    }

}
