package com.example.fanp.presentation.mqtt.broker.taglist;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityLocalbrokertaglistBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class Localbrokertaglist extends DaggerAppCompatActivity implements AdapterImp{


    public ActivityLocalbrokertaglistBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    public LocalBrokerTagViewModel viewmodel;

    @Inject
    I4AllSettingDao db;

    @Inject
    Context ctx;

    AdapterTagList adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_localbrokertaglist);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(LocalBrokerTagViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;
        setContentView(binding.getRoot());


        refresh();



    }

    public void refresh(){
        ArrayList<tagmodel> list = new ArrayList<>();
        List<I4AllSetting> sampledata = db.gettags();
        for (int i = 0; i < sampledata.size(); i++) {
            try {
                JSONObject object = new JSONObject(sampledata.get(i).getItemsData());
                tagmodel model = new tagmodel(object.getString("tagname"),object.getString("topicname"),object.getString("tagtype"),sampledata.get(i));
                list.add(model);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        adapter = new AdapterTagList(list, this,this);
        binding.recTaglist.setLayoutManager(manager);
        binding.recTaglist.setAdapter(adapter);
    }

    @Override
    public void Delete(tagmodel item) {
        db.delete(item.root);
        refresh();

    }

    @Override
    public void Edit(tagmodel item) throws JSONException {
        JSONObject object = new JSONObject(item.root.getItemsData());


        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_broker_tag);

        EditText edt_name = (EditText) dialog.findViewById(R.id.edt_name);
        edt_name.setText(item.name);
        EditText edt_topic = (EditText) dialog.findViewById(R.id.edt_topic);
        edt_topic.setText(item.topic);
        EditText edt_schedule = (EditText) dialog.findViewById(R.id.edt_schedule);
        edt_schedule.setText(object.getString("tagschedule"));
        EditText edt_des = (EditText) dialog.findViewById(R.id.edt_des);
        edt_des.setText(object.getString("des"));

        CheckBox chb_system = (CheckBox) dialog.findViewById(R.id.chb_system);
        if (object.getString("systemname").equals("0")){
            chb_system.setChecked(false);
        }else
            chb_system.setChecked(true);
        Button btnadd = (Button) dialog.findViewById(R.id.btn_add);
        btnadd.setText(getResources().getString(R.string.edit));
        Button btnexit = (Button) dialog.findViewById(R.id.btnexit);
        Spinner sp_type = (Spinner) dialog.findViewById(R.id.sp_type);
        switch (object.getString("tagtype")){
            case "CSV":
                sp_type.setSelection(2);
                break;
            case "Json":
                sp_type.setSelection(1);
                break;
            case "Plain Text":
                sp_type.setSelection(0);
                break;
        }
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();
                try {
                    object.put("tagname", edt_name.getText().toString());
                    object.put("tagtype", sp_type.getSelectedItem().toString());
                    object.put("tagschedule", edt_schedule.getText().toString());
                    object.put("systemname", chb_system.isChecked());
                    object.put("topicname", edt_topic.getText().toString());
                    object.put("des", edt_des.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                I4AllSetting data = new I4AllSetting(0, 501, object.toString(), false, timeStamp);
                db.insert(data);
                db.delete(item.root);
                dialog.dismiss();
                refresh();

            }
        });

        switch (object.getString("tagtype")){
            case "CSV":
                sp_type.setSelection(2);
                break;
            case "Json":
                sp_type.setSelection(1);
                break;
            case "Plain Text":
                sp_type.setSelection(0);
                break;
        }
        dialog.show();


    }
}