package com.example.fanp.presentation.s7.manageplc.functionblock.parameters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityMainParameterBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.manageplc.datablockplc.AdapterDataBlockPlcList;
import com.example.fanp.presentation.s7.manageplc.datablockplc.DatablockplcViewModel;
import com.example.fanp.presentation.s7.manageplc.functionblock.parameters.addparameter.AddParameterFunctionBlock;
import com.example.fanp.utils.Toaster;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainParameter extends DaggerAppCompatActivity implements ListParameterImpl {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    @Inject
    I4AllSettingDao db;

    public static I4AllSetting item;

    public AdapterPrameterFunctionBloc adapter;

    public ActivityMainParameterBinding binding;
    public MainParameterViewModel viewmodel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_parameter);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(MainParameterViewModel.class);
        binding.setViewmodel(viewmodel);

        binding.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddParameterFunctionBlock.item=item;
                AddParameterFunctionBlock.update=false;
                startActivity(new Intent(getApplicationContext(),AddParameterFunctionBlock.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh(){
        try {
            JSONObject object = new JSONObject(item.getItemsData());
            List<I4AllSetting> items = db.getitembyitesref(object.getInt("functionblocknumber"));
            LinearLayoutManager manager = new LinearLayoutManager(this);
            adapter = new AdapterPrameterFunctionBloc(this, this, items);
            binding.recyclerView.setLayoutManager(manager);
            binding.recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(I4AllSetting item) {
        db.delete(item);
        refresh();
    }

    @Override
    public void edit(I4AllSetting item) {

    }
}