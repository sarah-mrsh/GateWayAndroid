package com.example.fanp.presentation.protocolmanagment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import com.example.fanp.R;
import com.example.fanp.domain.entity.ProtocolTypeEntity;
import com.example.fanp.presentation.modbus.rtu.MainModBusRTU;
import com.example.fanp.presentation.modbus.tcp.MainModbusTCP;
import com.example.fanp.presentation.mqtt.clientmqtt.MainMqttClient;
import com.example.fanp.presentation.s7.MainS7;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MprotocolManagmentViewModel extends ViewModel {


    private List<ProtocolTypeEntity> protocolListfalse =  new ArrayList<>();
    private ProtocolAdapter protocolAdapter;
//    ProtocolsType protocolsType;

    @SuppressLint("StaticFieldLeak")
    MProtocolManagment main;

    @Inject
    public MprotocolManagmentViewModel(){}


    public void start_MainModBus(){
        main.startActivity(new Intent(main, MainModbusTCP.class));
    }


    public void start_MainModBusRTU(){
        main.startActivity(new Intent(main, MainModBusRTU.class));
    }


    public void start_MainS7(){
        main.startActivity(new Intent(main, MainS7.class));
    }

    public void start_MainMqttClient(){
        main.startActivity(new Intent(main, MainMqttClient.class));
    }
    public void cls_app(){
        main.onBackPressed();
    }

    public void finish() {
        main.finish();
    }

    public void add(){
        showPortRequestDialog();
    }


    private void showPortRequestDialog() {
        final Dialog dialog = new Dialog(main);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_port_request);
        dialog.setCancelable(true);
        ((Button) dialog.findViewById(R.id.bt_cancel)).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                Toast.makeText(main, "انصراف", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                protocolAdapter.notifyDataSetChanged();

            }
        });

        ((Button) dialog.findViewById(R.id.button_port_request)).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                protocolAdapter.notifyDataSetChanged();

            }
        });

        dialog.show();
    }


//    private void addProtocolButtons(Dialog d)
//    {
//        LinearLayout layout = (LinearLayout) d.findViewById(R.id.layout_time_desc1);
//        layout.setOrientation(LinearLayout.VERTICAL);  //Can also be done in xml by android:orientation="vertical"
//        for (int i = 0; i < protocolListfalse.size(); i++) {
//            LinearLayout row = new LinearLayout(main);
//            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(0,3,0,3);
//            row.setLayoutParams(params);
//            Button btnTag = new Button(main);
//            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            btnTag.setBackground(ContextCompat.getDrawable(main, R.drawable.transparent_white_dotline));
//            btnTag.setPadding(2,2,2,2);
//            //btnTag.setText("Button " + (1 + (i * 4)));
//            btnTag.setId( 1 + (i * 4));
//            btnTag.setTag(new Integer(0));
//            String a = protocolListfalse.get(i).getProtocolName();
//            btnTag.setText(a);
//
//            btnTag.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    if((Integer) v.getTag() == 0) {
//                        v.setBackground(ContextCompat.getDrawable(main, R.drawable.transparent_white_border));
//                        v.setTag(new Integer(1));
//                        Button btnClicked = (Button) v;
//                        switch ((String)btnClicked.getText()){
//                            case "MODBUS" :
//                                protocolsType.setModbus(true);
//                                break;
//                            case "SNMP" :
//                                protocolsType.setSNMP(true);
//                                break;
////                                case "SERIAL" :
////                                    protocolsType.setSERIAL(true);
////                                    break;
//                            case "COAP" :
//                                protocolsType.setCOAP(true);
//                                break;
//                            case "S7" :
//                                protocolsType.setS7(true);
//                                break;
//                            case "MQTT" :
//                                protocolsType.setMQTT(true);
//                                break;
//                        }
//                    } else{
//                        v.setBackground(ContextCompat.getDrawable(main, R.drawable.transparent_white_dotline));
//                        v.setTag(new Integer(0));
//                        Button btnClicked = (Button) v;
//                        switch ((String)btnClicked.getText()) {
//                            case "MODBUS":
//                                protocolsType.setModbus(false);
//                                break;
//                            case "SNMP":
//                                protocolsType.setSNMP(false);
//                                break;
////                                case "SERIAL":
////                                    protocolsType.setSERIAL(false);
////                                    break;
//                            case "COAP":
//                                protocolsType.setCOAP(false);
//                                break;
//                            case "S7":
//                                protocolsType.setS7(false);
//                                break;
//                            case "MQTT":
//                                protocolsType.setMQTT(false);
//                                break;
//                        }
//                    }
////                    Utility utility = new Utility();
////                    I4AllSetting i4AllSettingProtocolsAdd;
////                    Gson gsonProtocolsType = new Gson();
////                    String stringProtocolsTypes = gsonProtocolsType.toJson(protocolsType);
////                    i4AllSettingProtocolsAdd = new I4AllSetting(400, AllSettingIDs.PROTOCOLSTYPE.getValue(), stringProtocolsTypes, false, utility.getCurrentTime());
////                    dbHandler.updateI4AllSetting(i4AllSettingProtocolsAdd);
////                    fillProtocolList();
//                }
//
//
//            });
//
//            row.addView(btnTag);
//            layout.addView(row);
//
//
//        }
//
//    }

}
