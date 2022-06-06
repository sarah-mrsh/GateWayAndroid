package com.example.fanp.presentation.specificsetting;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.fanp.R;
import com.example.fanp.presentation.modbus.rtu.MainModBusRTU;
import com.example.fanp.presentation.modbus.tcp.MainModbusTCP;
import com.example.fanp.presentation.mport.MportActivity;
import com.example.fanp.presentation.mqtt.broker.brokersetting.BrokerSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.MainMqttClient;
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


        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = getActivity().getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config,
                getActivity().getBaseContext().getResources().getDisplayMetrics());


        ImageButton building_button_port_button = (ImageButton) view.findViewById(R.id.building_button_port_button);
        building_button_port_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), MportActivity.class));
            }
        });
        //Now specific components here (you can initialize Buttons etc)

        view.findViewById(R.id.txtmqtt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BrokerSetting.class));
            }
        });
        view.findViewById(R.id.txtmodbus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainModbusTCP.class));
            }
        });
        view.findViewById(R.id.txtmodbusrtu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainModBusRTU.class));
            }
        });
        view.findViewById(R.id.txts7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainS7.class));
            }
        });

        view.findViewById(R.id.txtmqttclient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainMqttClient.class));
            }
        });

        return view;
    }


}
