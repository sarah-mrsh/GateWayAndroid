package com.example.fanp.utils;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.sql.Timestamp;

public class autoIdEdt {

    public String gen(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            return timestamp.toString().replaceAll(" ","").replaceAll("-","").replaceAll(":","").replaceAll("\\.","").substring(10,16);//TODO It has an error sometimes
        }catch (Exception ex){
            Log.e("APPFANAP",ex.getMessage().toString());
            return "11111";
        }
    }





}
