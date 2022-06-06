package com.example.fanp.presentation.wifi.savednetworks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fanp.R;
import com.example.fanp.domain.local.data.WifiDao;
import com.example.fanp.domain.local.repository.WifiSetting;
import com.example.fanp.presentation.wifi.WifiAdapter;
import com.example.fanp.presentation.wifi.wifi;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SavedNetworks extends DaggerAppCompatActivity implements SavedImp{


    @Inject
    WifiDao db;





    List<WifiSetting> data;

    SavedWifiAdapter adapter;


    RecyclerView recyclerViewsaved;
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_networks);

        recyclerViewsaved = (RecyclerView) findViewById(R.id.recsaved);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refresh();
    }

    public void refresh(){
        data = db.all();
        adapter = new SavedWifiAdapter(data, this, this);
        recyclerViewsaved.setAdapter(adapter);
        recyclerViewsaved.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void forget(WifiSetting item) {
        db.delete(item);
        refresh();
    }
}