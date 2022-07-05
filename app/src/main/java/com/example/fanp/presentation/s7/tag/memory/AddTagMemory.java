package com.example.fanp.presentation.s7.tag.memory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddTagMemoryBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddTagMemory extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Context xcs;


    public static I4AllSetting data;
    public static boolean update = false;

    public AddTagMemoryViewModel viewmodel;

    public ActivityAddTagMemoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tag_memory);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tag_memory);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddTagMemoryViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;


        if (update) {
            update = false;
            binding.spplc.setEnabled(false);
            binding.edttagid.setEnabled(false);

            try {
                JSONObject object = new JSONObject(data.getItemsData());

//            object.put("tagname", tagname);
//            object.put("tagid", tagid);
//            object.put("plc", plc);
//            object.put("type", type);
//            object.put("address", address);
//            object.put("bitnumber", bitnumber);
//            object.put("function", function);
//            object.put("description", description);

                viewmodel.tagname = object.getString("tagname");
                viewmodel.tagid = object.getString("tagid");
                viewmodel.type = object.getString("type");
                viewmodel.address = object.getString("address");
                viewmodel.bitnumber = object.getString("bitnumber");
                viewmodel.function = object.getString("function");
                viewmodel.description = object.getString("description");
                viewmodel.plc = object.getString("plc");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


}