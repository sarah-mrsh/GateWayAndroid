package com.example.fanp.presentation.mqtt.broker.clientlist;

import com.example.fanp.domain.local.repository.I4AllSetting;

public class clientmodel {

    String name;
    String id;
    String qos;
    String op;//sub or pub
    I4AllSetting root;

    public clientmodel(String name, String id, String qos, String op, I4AllSetting root) {
        this.name = name;
        this.id = id;
        this.qos = qos;
        this.op = op;
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQos() {
        return qos;
    }

    public void setQos(String qos) {
        this.qos = qos;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public I4AllSetting getRoot() {
        return root;
    }

    public void setRoot(I4AllSetting root) {
        this.root = root;
    }
}
