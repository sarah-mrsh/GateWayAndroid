package com.example.fanp.presentation.modbus.rtu.taglist;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.modbus.rtu.tag.AddTag;

import javax.inject.Inject;

public class TagListViewModel extends ViewModel {


    TagListRtu main;

    @Inject
    public  TagListViewModel(){}


    public void addtag(){
        AddTag.item = TagListRtu.item;
        AddTag.index = TagListRtu.index;
        main.startActivity(new Intent(main, AddTag.class));
    }

    public void exit(){
        main.finish();
    }

}
