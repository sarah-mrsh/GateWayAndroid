package com.example.fanp.presentation.generalsetting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.fanp.R;
import com.example.fanp.presentation.protocolmanagment.MProtocolManagment;
import com.example.fanp.utils.BasicFragment;

import javax.inject.Inject;

public class GeneralSettingFragment extends BasicFragment {


    @Inject
    public GeneralSettingFragment(){}


    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_setting,parent,false);

        //Now specific components here (you can initialize Buttons etc)
        RelativeLayout setting_button_protocol_layout = (RelativeLayout) view.findViewById(R.id.setting_button_protocol_layout);
        setting_button_protocol_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MProtocolManagment.class));
            }
        });

        return view;
    }
}
