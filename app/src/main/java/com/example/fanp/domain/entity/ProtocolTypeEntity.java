package com.example.fanp.domain.entity;

public class ProtocolTypeEntity {


    private Integer protocolId;

    private String protocolName;


    public ProtocolTypeEntity(Integer protocolId, String protocolName) {
        this.protocolId = protocolId;
        this.protocolName = protocolName;
    }

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }
}

