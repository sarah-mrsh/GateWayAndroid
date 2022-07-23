package com.example.fanp.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.example.fanp.R;
import com.example.fanp.domain.local.repository.I4AllSetting;

public class ParameterDialog extends Dialog {







    public ParameterDialog(@NonNull Context context,I4AllSetting function) {
        super(context);

        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_parameter_list,null,false));


    }
}
