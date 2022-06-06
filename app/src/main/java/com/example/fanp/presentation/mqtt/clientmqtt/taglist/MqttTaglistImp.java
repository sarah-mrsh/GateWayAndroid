package com.example.fanp.presentation.mqtt.clientmqtt.taglist;

import com.example.fanp.domain.local.repository.I4AllSetting;

public interface MqttTaglistImp {
    void delete(I4AllSetting item);
    void update(I4AllSetting item);
}
