package com.example.fanp.presentation.mqtt.broker.editbroker;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class EditBrokerViewModel extends ViewModel {


    @Inject
    public EditBrokerViewModel() {}


   public void isCheckedSendTimeStamp(Boolean checked){
       Log.i("isCheckedSendTimeStamp","isCheckedSendTimeStamp");
    }

   public String  aa(){
         return "abed";
    }

}
