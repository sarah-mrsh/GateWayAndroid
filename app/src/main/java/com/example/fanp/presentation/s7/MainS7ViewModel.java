package com.example.fanp.presentation.s7;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.s7.manageplc.ManagePLC;
import com.example.fanp.presentation.s7.tag.MainTag;

import javax.inject.Inject;

public class MainS7ViewModel extends ViewModel {


    MainS7 main;

    @Inject
    Context ctx;

    @Inject
    public MainS7ViewModel(){}


    public void manageplc(){
        ctx.startActivity(new Intent(ctx, ManagePLC.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
    public void tagmanage(){
        ctx.startActivity(new Intent(ctx, MainTag.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void finish(){
        main.finish();
    }

}
