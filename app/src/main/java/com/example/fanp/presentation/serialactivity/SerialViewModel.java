package com.example.fanp.presentation.serialactivity;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;



public class SerialViewModel extends ViewModel {


//todo

    public SerialActivity main;
    @Inject
    public SerialViewModel(){}



    public void exit(){
        main.finish();
    }




}
