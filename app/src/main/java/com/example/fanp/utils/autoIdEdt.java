package com.example.fanp.utils;


import android.content.Context;
import android.widget.Toast;

import java.sql.Timestamp;

public class autoIdEdt {






    public String gen(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String tm =  timestamp.toString().replaceAll(" ","").replaceAll("-","").replaceAll(":","").replaceAll("\\.","").substring(10,16);
        return tm;
    }





}
