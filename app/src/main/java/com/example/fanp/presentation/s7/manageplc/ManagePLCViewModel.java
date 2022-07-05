package com.example.fanp.presentation.s7.manageplc;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.s7.manageplc.datablockplc.DataBlockPlc;
import com.example.fanp.presentation.s7.manageplc.functionblock.FunctionBlockMainPlc;
import com.example.fanp.presentation.s7.plclist.PLCList;

import javax.inject.Inject;

public class ManagePLCViewModel extends ViewModel {


    @Inject
    Context ctx;

    ManagePLC main;

    @Inject
    public ManagePLCViewModel(){}



    public void plclist(){
        main.startActivity(new Intent(main, PLCList.class));
    }

    public void datablockplc(){
        main.startActivity(new Intent(main, DataBlockPlc.class));
    }

    public void functionblock(){
        main.startActivity(new Intent(main, FunctionBlockMainPlc.class));
    }

    public void finish(){
        main.finish();
    }


}
