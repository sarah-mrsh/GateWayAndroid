package com.example.fanp.presentation.s7.manageplc.adddatablockplc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddDataBlockPlcBinding;
import com.example.fanp.databinding.ActivityAddDatatBlockTagBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.manageplc.datablockplc.DatablockplcViewModel;
import com.example.fanp.presentation.s7.tag.io.AdapterIOtaglist;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddDataBlockPlc extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;

    @Inject
    I4AllSettingDao db;


    ActivityAddDataBlockPlcBinding binding;
    AddDataBlockPlcViewModel viewmodel;

    public static boolean Update;
    public static I4AllSetting item;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_data_block_plc);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddDataBlockPlcViewModel.class);
        binding.setViewmodel(viewmodel);


        binding.chbsharedb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                binding.linfunction.setVisibility(View.INVISIBLE);
            }else{
                binding.linfunction.setVisibility(View.VISIBLE);
            }
        });



        List<String> functions = new ArrayList<>();
        List<I4AllSetting> functionsblock = db.getitembyitesref(601);
        for (I4AllSetting item : functionsblock) {
            try {
                JSONObject object = new JSONObject(item.getItemsData());
                functions.add(object.getString("functionblockname"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (functions.size() == 0) {
            Toast.makeText(xcs, "Function IS NULL", Toast.LENGTH_SHORT).show();
            finish();
        }
        ArrayAdapter<String> adapterfunction = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, functions);
        adapterfunction.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);








        binding.spfunctionblock.setAdapter(adapterfunction);


        viewmodel.main=this;


        binding.btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (Update){
            Update=false;
            binding.tagid.setEnabled(false);
            try {
                JSONObject object = new JSONObject(item.getItemsData());
                viewmodel.id = object.getString("id");
                viewmodel.name = object.getString("name");
                viewmodel.functionblock = object.getString("functionblock");
                viewmodel.description = object.getString("description");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}