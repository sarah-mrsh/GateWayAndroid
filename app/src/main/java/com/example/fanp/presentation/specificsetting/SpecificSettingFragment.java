package com.example.fanp.presentation.specificsetting;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.example.fanp.R;
import com.example.fanp.presentation.modbus.rtu.MainModBusRTU;
import com.example.fanp.presentation.modbus.tcp.MainModbusTCP;
import com.example.fanp.presentation.mport.MportActivity;
import com.example.fanp.presentation.mqtt.broker.brokersetting.BrokerSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.MainMqttClient;
import com.example.fanp.presentation.protocolmanagment.MProtocolManagment;
import com.example.fanp.presentation.s7.MainS7;
import com.example.fanp.utils.BasicFragment;

import java.util.Locale;

import javax.inject.Inject;

public class SpecificSettingFragment extends BasicFragment {


    @Inject
    public SpecificSettingFragment() {
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_setting, parent, false);




        ImageButton building_button_port_button = (ImageButton) view.findViewById(R.id.building_button_port_button);
        building_button_port_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), MportActivity.class));
            }
        });

        ImageButton building_button_protocol_button = (ImageButton) view.findViewById(R.id.building_button_protocol_button);
        building_button_protocol_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), MProtocolManagment.class));
            }
        });
        //Now specific components here (you can initialize Buttons etc)


////////////////////////////////sara


        return view;
    }


}
