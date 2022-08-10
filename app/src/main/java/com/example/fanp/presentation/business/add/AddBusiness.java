package com.example.fanp.presentation.business.add;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddBusinessBinding;
import com.example.fanp.databinding.ActivityAddTagMqttClientBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.addtag.AddTagMqttViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddBusiness extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    ActivityAddBusinessBinding binding;
    AddBusinessViewModel viewmodel;

    public static I4AllSetting ddbdata;
    public static boolean update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_business);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddBusinessViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;


        if (update) {
            binding.edttagid.setEnabled(false);
            binding.buttonProtocolCancelrequestText.setText(this.getResources().getString(R.string.save));
            if (ddbdata != null)
                try {
                    JSONObject data = new JSONObject(ddbdata.getItemsData());
                    if (data.has("name")) {
                        viewmodel.name = (data.getString("name"));
                    }
                    if (data.has("id")) {
                        viewmodel.id = (data.getString("id"));
                    }
                    if (data.has("trpersec")) {
                        viewmodel.trpersec = (data.getString("trpersec"));
                    }
                    if (data.has("size")) {
                        viewmodel.size = (data.getString("size"));
                    }
                    if (data.has("latestdata")) {
                        viewmodel.latestdata = (data.getBoolean("latestdata"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            update = false;
        }


    }
}