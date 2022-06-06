package com.example.fanp.presentation.s7.tag.datablock;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class TagListDataBlockViewModel extends ViewModel {


    TagListDatablock main;

    @Inject
    public TagListDataBlockViewModel(){}

    public void exit(){
        main.finish();
    }

    public void addtag(){
        main.startActivity(new Intent(main,AddDatatBlockTag.class));
    }
}
