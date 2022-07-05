package com.example.fanp.presentation.mqtt.broker.topic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fanp.R;
import com.example.fanp.databinding.AddTopicBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.broker.clientlist.clientmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class Addtopic extends DaggerAppCompatActivity implements TopicAdapter {


    public static clientmodel model;


    public Addtopic() {
    }


    AddTopicBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    public AddTopicViewModel viewmodel;

    @Inject
    I4AllSettingDao db;

    @Inject
    Context ctx;

    AdapterTopic adapter;


    ArrayList<topicmodel> list = new ArrayList<>();

    I4AllSetting clientlist = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.add_topic);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddTopicViewModel.class);
        setContentView(binding.getRoot());


        clientlist = db.gettopics(model.getRoot().getAllSettingId());//client id

        if (clientlist != null) {
            try {
                JSONArray array = new JSONArray(clientlist.getItemsData());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    list.add(new topicmodel(object.getString("clientname"),
                            object.getString("name"),
                            object.getString("qos"),
                            object.getBoolean("retain"),
                            object.getBoolean("privated"), model));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            refresh();
        } else {
            init();
        }


        binding.imgsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void refresh() {
        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        adapter = new AdapterTopic(list, this, this);
        binding.rec.setLayoutManager(manager);
        binding.rec.setAdapter(adapter);
    }

    public void init() {
        list.add(new topicmodel("", "", "", false, false, model));
        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        adapter = new AdapterTopic(list, this, this);
        binding.rec.setLayoutManager(manager);
        binding.rec.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void addrow() {
        list.add(new topicmodel("", "", "", false, false, model));
        adapter.notifyItemInserted(adapter.getItemCount());
    }


    public void save() {

        JSONArray array = new JSONArray();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            AdapterTopic.ViewHoldertopic holdertopic = (AdapterTopic.ViewHoldertopic) binding.rec.findViewHolderForAdapterPosition(i);
            assert holdertopic != null;
            if (!holdertopic.name.getText().toString().isEmpty()) {
                JSONObject object = new JSONObject();
                try {
                    object.put("clientname", holdertopic.clientname.getText().toString());
                    object.put("name", holdertopic.name.getText().toString());
                    object.put("qos", holdertopic.qos.getSelectedItem().toString());
                    object.put("retain", holdertopic.retain.isChecked());
                    object.put("privated", holdertopic.privaited.isChecked());
                    array.put(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        if (clientlist == null) {
            if (array.length() > 0) {
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                I4AllSetting data = new I4AllSetting(0, model.getRoot().getAllSettingId(), array.toString(), false, timeStamp);
                db.insert(data);
            }

        } else {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            clientlist.setItemsData(array.toString());
            clientlist.setDateTime(timeStamp);
            db.update(clientlist);
        }


        finish();

    }
}
