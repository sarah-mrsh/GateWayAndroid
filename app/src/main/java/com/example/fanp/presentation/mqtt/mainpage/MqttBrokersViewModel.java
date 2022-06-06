package com.example.fanp.presentation.mqtt.mainpage;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fanp.presentation.mqtt.broker.editbroker.EditBroker;

import com.example.fanp.presentation.mqtt.presenter.BrokerImp;

import java.util.ArrayList;

import javax.inject.Inject;

public class MqttBrokersViewModel extends ViewModel implements BrokerImp {



    @Inject
    Context ctx;

    BrokersListAdapter adapter;

    @Inject
    MqttBrokersViewModel(){}

    public void testabed(){
        Toast.makeText(ctx, "salam", Toast.LENGTH_SHORT).show();
    }


    public void getdata(RecyclerView rec){
        //todo get data from dao
        ArrayList<String> sampledata = new ArrayList<>();
        sampledata.add("hi");
        sampledata.add("bye");
        sampledata.add("how");
        sampledata.add("hi");
        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        adapter = new BrokersListAdapter(sampledata,this,ctx);
        rec.setLayoutManager(manager);
        rec.setAdapter(adapter);
    }


    public void edit(){
        // todo create an activity in order to edit a record
        Toast.makeText(ctx, "new activity", Toast.LENGTH_SHORT).show();
        Intent edit = new Intent(ctx, EditBroker.class);
        edit.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(edit);
//        ctx.startActivity(new Intent(ctx,));
    }
    public void add(){
        // todo create an activity in order to add a record
        Toast.makeText(ctx, "new activity", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void test(int id) {
        Toast.makeText(ctx, String.valueOf(id), Toast.LENGTH_SHORT).show();
    }
}
