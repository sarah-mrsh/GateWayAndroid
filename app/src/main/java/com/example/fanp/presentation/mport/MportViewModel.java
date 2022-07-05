package com.example.fanp.presentation.mport;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.lan.LanActivity;
import com.example.fanp.presentation.serialactivity.SerialActivity;
import com.example.fanp.presentation.wifi.WifiActivity;

import javax.inject.Inject;

public class MportViewModel extends ViewModel {


    MportActivity main;

    @Inject
    public MportViewModel(){}

//09125935746
    public void finish() {
    main.finish();
}
    public void lan(int i){
     main.startActivity(new Intent(main, LanActivity.class));
    }

    public void serial(){
        main.startActivity(new Intent(main, SerialActivity.class));
    }


    public void wifi(){
        main.startActivity(new Intent(main, WifiActivity.class));

    }


}
