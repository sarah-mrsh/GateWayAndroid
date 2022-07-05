package com.example.fanp.presentation.s7.tag.io;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddTagIoBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.tag.datablock.AddTagDataBlockViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddTagIo extends DaggerAppCompatActivity{


    @Inject
    ViewModelProviderFactory providerFactory;

    ActivityAddTagIoBinding binding;
    AddTagIOViewmodel viewmodel;




    public static boolean update = false;
    public static I4AllSetting item;



    @Inject
    Context xcs;



    @Inject
    I4AllSettingDao db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_tag_io);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tag_io);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddTagIOViewmodel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;


        List<I4AllSetting> spdata = db.getplc();
        List<String> plcs = new ArrayList<>();
        for (I4AllSetting item : spdata) {
            try {
                JSONObject object = new JSONObject(item.getItemsData());
                plcs.add(object.getString("devicename"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (plcs.size() == 0) {
            Toast.makeText(xcs, "PLC IS NULL", Toast.LENGTH_SHORT).show();
            finish();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, plcs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spplc.setAdapter(adapter);


        if (update) {
            binding.tagid.setEnabled(false);
            binding.spplc.setEnabled(false);
            try {
                JSONObject object = new JSONObject(item.getItemsData());
                if (object.has("tagname")) {
                    viewmodel.tagname = object.getString("tagname");
                }
                if (object.has("tagid")) {
                    viewmodel.tagid = object.getString("tagid");
                }
                if (object.has("plc")) {
                    for (int i=0;i<plcs.size();i++){
                        if (plcs.get(i).equals(object.getString("plc"))){
                            binding.spplc.setSelection(i);
                            break;
                        }
                    }
//                    viewmodel.plc = object.getString("plc");
                }
                if (object.has("tagtype")) {
                    viewmodel.tagtype = object.getString("tagtype");
                }
                if (object.has("iotype")) {
                    viewmodel.iotype = object.getString("iotype");
                }
                if (object.has("description")) {
                    viewmodel.description = object.getString("description");
                }
                if (object.has("bitcount")) {
                    viewmodel.bitcount = object.getString("bitcount");
                }
                if (object.has("bytecount")) {
                    viewmodel.bytecount = object.getString("bytecount");
                }
                if (object.has("wordcount")) {
                    viewmodel.wordcount = object.getString("wordcount");
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
            update = false;
        }


    }


}