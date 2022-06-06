package com.example.fanp.presentation.s7.tag.io;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class TagListIOViewModel extends ViewModel {



    TagListIo main;


    @Inject
    public TagListIOViewModel(){}


    public void addio(){
        main.startActivity(new Intent(main,AddTagIo.class));
    }

    public void exit(){
        main.finish();
    }

}
