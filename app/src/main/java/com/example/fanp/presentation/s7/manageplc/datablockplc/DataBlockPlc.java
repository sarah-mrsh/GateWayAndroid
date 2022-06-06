package com.example.fanp.presentation.s7.manageplc.datablockplc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityDataBlockPlcBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.addplc.AddPlcViewModel;
import com.example.fanp.presentation.s7.manageplc.adddatablockplc.AddDataBlockPlc;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DataBlockPlc extends AppCompatActivity {

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    @Inject
    I4AllSettingDao db;


    ActivityDataBlockPlcBinding binding;
    DatablockplcViewModel viewmodel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_block_plc);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(DatablockplcViewModel.class);
        binding.setViewmodel(viewmodel);



        binding.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddDataBlockPlc.class));
            }
        });



    }

    public void refresh() {
        List<I4AllSetting> data = new ArrayList<>();
        List<I4AllSetting> spdata = db.getplc();
        List<String> plcs = new ArrayList<>();
        for (I4AllSetting item : spdata) {
            try {
                JSONObject object = new JSONObject(item.getItemsData());
                plcs.add(object.getString("deviceid"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for (String item : plcs) {
            List<I4AllSetting> items = db.getitembyitesref(Integer.parseInt(item));
            for (I4AllSetting dataitem : items) {

            }

        }


//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        adapter = new AdapterIOtaglist(this, this, data);
//        binding.recio.setLayoutManager(manager);
//        binding.recio.setAdapter(adapter);

    }
}