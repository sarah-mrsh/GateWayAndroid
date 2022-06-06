package com.example.fanp.presentation.mqtt.broker.clientlist;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityClientListBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.broker.topic.Addtopic;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ClientList extends DaggerAppCompatActivity implements AdapterClientImp {


    ClientListViewModel viewmodel;
    ActivityClientListBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context ctx;

    @Inject
    I4AllSettingDao db;


    ClientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_client_list);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(ClientListViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;
        setContentView(binding.getRoot());

        refresh();
    }

    public void refresh() {
        ArrayList<clientmodel> list = new ArrayList<>();
        List<I4AllSetting> sampledata = db.getclients();
        for (int i = 0; i < sampledata.size(); i++) {
            try {
                JSONObject object = new JSONObject(sampledata.get(i).getItemsData());
                clientmodel model = new clientmodel(object.getString("clientname"), object.getString("clientid"),
                        object.getString("qos"), object.getString("op"), sampledata.get(i));
                list.add(model);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        adapter = new ClientAdapter(list, this, this);
        binding.recclient.setLayoutManager(manager);
        binding.recclient.setAdapter(adapter);
    }

    @Override
    public void Delete(clientmodel item) {
        if (db.gettopics(item.root.getAllSettingId())!=null){
            db.delete(db.gettopics(item.root.getAllSettingId()));
        }
        db.delete(item.root);
        refresh();
    }

    @Override
    public void Edit(clientmodel item) throws JSONException {


        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_client);
        Button btnadd = (Button) dialog.findViewById(R.id.btnadd);
        btnadd.setText(getResources().getString(R.string.edit));

        EditText edtname = (EditText) dialog.findViewById(R.id.edtname);
        EditText edtclientid = (EditText) dialog.findViewById(R.id.edtclientid);
        Spinner spnumber = (Spinner) dialog.findViewById(R.id.spnumber);
        Spinner spsubpub = (Spinner) dialog.findViewById(R.id.sppubsub);

        edtname.setText(item.name);
        edtclientid.setText(item.id);
        switch (item.qos){
            case "0":spnumber.setSelection(0);break;
            case "1":spnumber.setSelection(1);break;
            case "2":spnumber.setSelection(2);break;
        }
        switch (item.op){
            case "ALL":spsubpub.setSelection(0);break;
            case "Sub":spsubpub.setSelection(1);break;
            case "Pub":spsubpub.setSelection(2);break;
        }

        dialog.findViewById(R.id.btnadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();


                try {
                    object.put("clientname", edtname.getText().toString());
                    object.put("clientid", edtclientid.getText().toString());
                    object.put("qos", spnumber.getSelectedItem().toString());
                    object.put("op", spsubpub.getSelectedItem().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                item.root.setItemsData(object.toString());
                item.root.setDateTime(timeStamp);
                db.update(item.root);
                dialog.dismiss();
                refresh();
            }
        });
        dialog.show();

    }

    @Override
    public void topic(clientmodel item) {
        Addtopic.model = item;
        startActivity(new Intent(this, Addtopic.class));
    }
}