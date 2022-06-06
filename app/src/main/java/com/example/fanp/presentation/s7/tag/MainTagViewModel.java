package com.example.fanp.presentation.s7.tag;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.s7.tag.datablock.TagListDatablock;
import com.example.fanp.presentation.s7.tag.io.AddTagIo;
import com.example.fanp.presentation.s7.tag.io.TagListIo;

import javax.inject.Inject;

public class MainTagViewModel extends ViewModel {


    MainTag main;
    @Inject
    public MainTagViewModel(){}


    public void exit(){
        main.finish();
    }

    public void datablocklist(){
        main.startActivity(new Intent(main, TagListDatablock.class));
    }

    public void tagio(){
        main.startActivity(new Intent(main, TagListIo.class));
    }



}
