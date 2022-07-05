package com.example.fanp.presentation.modbus.tcp.taglisttcp;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.modbus.rtu.tag.AddTag;
import com.example.fanp.presentation.modbus.rtu.taglist.TagListRtu;
import com.example.fanp.presentation.modbus.tcp.tag.AddTagTCP;

import javax.inject.Inject;

public class TagListTcpViewModel extends ViewModel {


    TaglistTcp main;
    @Inject
    public TagListTcpViewModel(){}


    public void addtag(){
        AddTagTCP.item = TaglistTcp.item;
        AddTagTCP.index = TaglistTcp.index;
        main.startActivity(new Intent(main, AddTagTCP.class));
    }

    public void exit(){
        main.finish();
    }

}
