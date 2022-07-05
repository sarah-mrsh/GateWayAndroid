package com.example.fanp.presentation.s7.tag.memory;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class TagListMemoryVIewModel extends ViewModel {









    TaglistMemory main;

    @Inject
    public TagListMemoryVIewModel(){}


    public void addtag(){
        main.startActivity(new Intent(main,AddTagMemory.class));
    }

    public void exit(){
        main.finish();
    }
}
