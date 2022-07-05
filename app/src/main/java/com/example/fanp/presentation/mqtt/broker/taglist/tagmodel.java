package com.example.fanp.presentation.mqtt.broker.taglist;

import com.example.fanp.domain.local.repository.I4AllSetting;

public class tagmodel {

    String name;
    String topic;
    String type;
    I4AllSetting root;


    public tagmodel(String name, String topic, String type, I4AllSetting root) {
        this.name = name;
        this.topic = topic;
        this.type = type;
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public I4AllSetting getRoot() {
        return root;
    }

    public void setRoot(I4AllSetting root) {
        this.root = root;
    }
}
