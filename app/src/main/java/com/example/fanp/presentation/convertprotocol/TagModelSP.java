package com.example.fanp.presentation.convertprotocol;

public class TagModelSP {

    String name;
    String id;
    String spe;


    public TagModelSP(String name, String id, String spe) {
        this.name = name;
        this.id = id;
        this.spe = spe;
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

    public String getSpe() {
        return spe;
    }

    public void setSpe(String spe) {
        this.spe = spe;
    }
}
