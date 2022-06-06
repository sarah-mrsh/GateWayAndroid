package com.example.fanp.domain.local.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wifitbl")
public class WifiSetting {




    @PrimaryKey(autoGenerate = true)
    int WifiSettingId;

    @ColumnInfo(name = "nemtworkName")
    String nemtworkName;

    @ColumnInfo(name = "networkPassword")
    String networkPassword;

    @ColumnInfo(name = "ip")
    String ip;

    @ColumnInfo(name = "mask")
    String mask;

    @ColumnInfo(name = "dns")
    String dns;

    @ColumnInfo(name = "dhcp")
    Boolean dhcp;

    @ColumnInfo(name = "autoConnect")
    Boolean autoConnect;

    public WifiSetting() {
    }

    public WifiSetting(int wifiSettingId, String nemtworkName, String networkPassword, String ip, String mask, String dns, Boolean dhcp, Boolean autoConnect) {
        WifiSettingId = wifiSettingId;
        this.nemtworkName = nemtworkName;
        this.networkPassword = networkPassword;
        this.ip = ip;
        this.mask = mask;
        this.dns = dns;
        this.dhcp = dhcp;
        this.autoConnect = autoConnect;
    }

    public int getWifiSettingId() {
        return WifiSettingId;
    }

    public void setWifiSettingId(int wifiSettingId) {
        WifiSettingId = wifiSettingId;
    }

    public String getNemtworkName() {
        return nemtworkName;
    }

    public void setNemtworkName(String nemtworkName) {
        this.nemtworkName = nemtworkName;
    }

    public String getNetworkPassword() {
        return networkPassword;
    }

    public void setNetworkPassword(String networkPassword) {
        this.networkPassword = networkPassword;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public Boolean getDhcp() {
        return dhcp;
    }

    public void setDhcp(Boolean dhcp) {
        this.dhcp = dhcp;
    }

    public Boolean getAutoConnect() {
        return autoConnect;
    }

    public void setAutoConnect(Boolean autoConnect) {
        this.autoConnect = autoConnect;
    }

}
