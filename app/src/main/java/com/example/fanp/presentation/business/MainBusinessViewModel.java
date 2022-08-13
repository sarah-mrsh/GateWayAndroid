package com.example.fanp.presentation.business;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.business.add.AddBusiness;

import javax.inject.Inject;

public class MainBusinessViewModel extends ViewModel {


    public MainBusiness main;

    @Inject
    public MainBusinessViewModel() {

    }

    public void add() {
        main.startActivity(new Intent(main, AddBusiness.class));

    }

    public void exit() {
        main.finish();
    }
}
