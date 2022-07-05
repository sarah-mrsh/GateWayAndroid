package com.example.fanp.presentation.s7.tag.datablock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddDatatBlockTagBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddDatatBlockTag extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;

    @Inject
    I4AllSettingDao db;

    ActivityAddDatatBlockTagBinding binding;
    AddTagDataBlockViewModel viewmodel;

    public static boolean update = false;
    public static I4AllSetting item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_datat_block_tag);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddTagDataBlockViewModel.class);
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
                if (object.has("function")) {
                    viewmodel.function = object.getString("function");
                }
                if (object.has("datablockcount")) {
                    viewmodel.datablockcount = object.getString("datablockcount");
                }
                if (object.has("description")) {
                    viewmodel.description = object.getString("description");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            update = false;
        }


    }
}