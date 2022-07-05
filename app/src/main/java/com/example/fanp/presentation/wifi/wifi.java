package com.example.fanp.presentation.wifi;

public class wifi  {



    String name;
    int rate;
    boolean status;


    public wifi(String name, int rate, boolean status) {
        this.name = name;
        this.rate = rate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
