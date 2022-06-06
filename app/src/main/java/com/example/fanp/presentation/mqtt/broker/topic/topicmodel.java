package com.example.fanp.presentation.mqtt.broker.topic;

import com.example.fanp.presentation.mqtt.broker.clientlist.clientmodel;

public class topicmodel {

    String clientname;
    String name;
    String qos;
    boolean retain;
    boolean privated;
    clientmodel client;


    public topicmodel(String clientname, String name, String qos, boolean retain, boolean privated, clientmodel client) {
        this.clientname = clientname;
        this.name = name;
        this.qos = qos;
        this.retain = retain;
        this.privated = privated;
        this.client = client;
    }

    public String getclientname() {
        return clientname;
    }

    public void setclientname(String topic) {
        this.clientname = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQos() {
        return qos;
    }

    public void setQos(String qos) {
        this.qos = qos;
    }

    public boolean isRetain() {
        return retain;
    }

    public void setRetain(boolean retain) {
        this.retain = retain;
    }

    public boolean isPrivated() {
        return privated;
    }

    public void setPrivated(boolean privated) {
        this.privated = privated;
    }

    public clientmodel getClient() {
        return client;
    }

    public void setClient(clientmodel client) {
        this.client = client;
    }
}
