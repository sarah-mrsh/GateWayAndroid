package com.example.fanp.presentation.mqtt.clientmqtt;

import com.example.fanp.domain.local.repository.I4AllSetting;

import java.util.List;

public interface mqttClientImp {
    void update(I4AllSetting item);
    void delete(I4AllSetting item);
    void taglist(I4AllSetting item);
}
