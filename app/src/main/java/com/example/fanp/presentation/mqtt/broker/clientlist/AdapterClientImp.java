package com.example.fanp.presentation.mqtt.broker.clientlist;

import org.json.JSONException;

public interface AdapterClientImp {

    void Delete(clientmodel item);
    void Edit(clientmodel item) throws JSONException;
    void topic(clientmodel item);
}
