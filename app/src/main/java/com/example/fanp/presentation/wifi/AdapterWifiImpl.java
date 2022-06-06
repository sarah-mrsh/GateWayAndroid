package com.example.fanp.presentation.wifi;

import com.example.fanp.ConfigWifi;

import java.util.ArrayList;

public interface AdapterWifiImpl {
    void connect(wifi item);
    void getlist(ArrayList<wifi> list);
    void disconnect(String name);


    //wifichange state
    void changestate(Boolean state);

    //connection
    void success_wifi_connect(String ssid);
    void failed_wifi_connect(String error);


    //disconnection
    void success_wifi_disconnect();
    void failed_wifi_disconnect(String error);


    void get_wifi_config();
    void show_wifi_config(ConfigWifi result);

}
