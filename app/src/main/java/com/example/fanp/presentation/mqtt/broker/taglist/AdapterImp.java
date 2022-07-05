package com.example.fanp.presentation.mqtt.broker.taglist;

import org.json.JSONException;

public interface AdapterImp {

    void Delete(tagmodel item);
    void Edit(tagmodel item) throws JSONException;
}
