package com.example.fanp.utils;

import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Toaster<T> {


    public Toaster(List<T> list) {
        for (T t : list) {
            if (t.getClass().getName().equals("IpEdt")){

            }
            Log.e("APPS",t.getClass().getName().toString());

        }
    }
}
