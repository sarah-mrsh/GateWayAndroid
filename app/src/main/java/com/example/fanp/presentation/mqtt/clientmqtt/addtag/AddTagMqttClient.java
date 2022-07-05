package com.example.fanp.presentation.mqtt.clientmqtt.addtag;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddTagMqttClientBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.addclient.AddClientViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddTagMqttClient extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    ActivityAddTagMqttClientBinding binding;
    AddTagMqttViewModel viewmodel;

    public static I4AllSetting ddbdata;
    public static boolean update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tag_mqtt_client);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddTagMqttViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;


        if (update) {
            binding.edttagid.setEnabled(false);
            if (ddbdata != null)
                try {
                    JSONObject data = new JSONObject(ddbdata.getItemsData());
                    if (data.has("tagid")) {
                        viewmodel.tagid = (data.getString("tagid"));
                    }
                    if (data.has("tagname")) {
                        viewmodel.tagname = (data.getString("tagname"));
                    }
                    if (data.has("type")) {
                        viewmodel.type = (data.getString("type"));
                    }
                    if (data.has("topicname")) {
                        viewmodel.topicname = (data.getString("topicname"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            update = false;
        }


    }
}