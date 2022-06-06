package com.example.fanp.domain.local.repository;


import androidx.room.Entity;

@Entity()
public class MqttClient {

    private int id;
    private String name;
    private Boolean send_time_stamp;
    private String protocol;
    private String ip;
    private String port;
    private Boolean keep_alive;
    private String username;
    private String password;
    private Boolean compatible_mqtt3_1_1;
    private int re_connect_ms;
    private int timeout;
    Boolean keep_will;
    private String will_topic;
    private String qos_will;
    private Boolean clean_session;
    private String will_payload;
    public MqttClient(String name, int id, Boolean send_time_stamp, String protocol, String ip, String port, Boolean keep_alive, String username, String password, Boolean compatible_mqtt3_1_1, int re_connect_ms, int timeout, Boolean keep_will, String will_topic, String qos_will, Boolean clean_session, String will_payload) {
        this.name = name;
        this.id = id;
        this.send_time_stamp = send_time_stamp;
        this.protocol = protocol;
        this.ip = ip;
        this.port = port;
        this.keep_alive = keep_alive;
        this.username = username;
        this.password = password;
        this.compatible_mqtt3_1_1 = compatible_mqtt3_1_1;
        this.re_connect_ms = re_connect_ms;
        this.timeout = timeout;
        this.keep_will = keep_will;
        this.will_topic = will_topic;
        this.qos_will = qos_will;
        this.clean_session = clean_session;
        this.will_payload = will_payload;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Boolean getSend_time_stamp() {
        return send_time_stamp;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public Boolean getKeep_alive() {
        return keep_alive;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getCompatible_mqtt3_1_1() {
        return compatible_mqtt3_1_1;
    }

    public int getRe_connect_ms() {
        return re_connect_ms;
    }

    public int getTimeout() {
        return timeout;
    }

    public Boolean getKeep_will() {
        return keep_will;
    }

    public String getWill_topic() {
        return will_topic;
    }

    public String getQos_will() {
        return qos_will;
    }

    public Boolean getClean_session() {
        return clean_session;
    }

    public String getWill_payload() {
        return will_payload;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSend_time_stamp(Boolean send_time_stamp) {
        this.send_time_stamp = send_time_stamp;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setKeep_alive(Boolean keep_alive) {
        this.keep_alive = keep_alive;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompatible_mqtt3_1_1(Boolean compatible_mqtt3_1_1) {
        this.compatible_mqtt3_1_1 = compatible_mqtt3_1_1;
    }

    public void setRe_connect_ms(int re_connect_ms) {
        this.re_connect_ms = re_connect_ms;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setKeep_will(Boolean keep_will) {
        this.keep_will = keep_will;
    }

    public void setWill_topic(String will_topic) {
        this.will_topic = will_topic;
    }

    public void setQos_will(String qos_will) {
        this.qos_will = qos_will;
    }

    public void setClean_session(Boolean clean_session) {
        this.clean_session = clean_session;
    }

    public void setWill_payload(String will_payload) {
        this.will_payload = will_payload;
    }
}

