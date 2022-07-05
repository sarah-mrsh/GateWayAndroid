package com.example.fanp.presentation.wifi;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fanp.ConfigWifi;
import com.example.fanp.R;
import com.example.fanp.databinding.ActivityWifiBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.WifiDao;
import com.example.fanp.domain.local.repository.WifiSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class WifiActivity extends DaggerAppCompatActivity implements AdapterWifiImpl {


    ActivityWifiBinding binding;
    WifiViewModel viewmodel;
    ArrayList<String> arrayList = new ArrayList<>();


    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    WifiDao db;

    @Inject
    Context xcs;

    @Inject
    WifiDao wifidb;

    WifiAdapter adapter;



    public static  wifi modelauto = null;



    public static ArrayList<wifi> list = new ArrayList<>();


    @Override
    protected void onResume() {
        super.onResume();
        binding.progress.setVisibility(View.VISIBLE);
        viewmodel.wifilist();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wifi);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(WifiViewModel.class);
        viewmodel.listener = this;
        binding.setViewmodel(viewmodel);
        setContentView(binding.getRoot());


        refresh();
    }

    public void clean() {
//        for (wifi item:list){
//            item.setStatus(false);
//        }
    }


    public void disconnect() {
        loading_on();
        for (wifi item : list) {
            if (item.status) {
                viewmodel.disconnect(item.name);
                item.status = false;
                break;
            }
        }
        refresh();

    }

    public void refresh() {
        Collections.sort(list, new Comparator<wifi>() {//TODO
            @Override
            public int compare(wifi w, wifi t1) {
                int ap1 = w.getRate();
                int ap2 = t1.getRate();
                return (ap2 - ap1);
            }
        });

        adapter = new WifiAdapter(list, this, this, db.all());
        binding.recycleMqttBrokers.setAdapter(adapter);
        binding.recycleMqttBrokers.setLayoutManager(new LinearLayoutManager(this));
    }

    Dialog dialog_connect;

    @Override
    public void connect(wifi model) {


        List<WifiSetting> list = db.all();
        for (WifiSetting item : list) {
            if (item.getNemtworkName().equals(model.name)) {
                viewmodel.connect(model.name, item.getNetworkPassword());
                loading_on();
                return;
            }
        }


        dialog_connect = new Dialog(this);
        dialog_connect.setContentView(R.layout.dialog_password_wifi);
        EditText edt_pass = (EditText) dialog_connect.findViewById(R.id.txtpassword);
        Button btncon = (Button) dialog_connect.findViewById(R.id.btncon);
        TextView title = (TextView) dialog_connect.findViewById(R.id.title);
        title.setText(model.name);
        Button bt_cancel = (Button) dialog_connect.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_connect.dismiss();
            }
        });
        btncon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewmodel.connect(model.name, edt_pass.getText().toString());
                loading_on();
                dialog_connect.dismiss();
            }
        });


        dialog_connect.show();

//        clean();
//        //todo find item and set
//        for (wifi item:list) {
//            if (item.getName().equals(model.getName())){
//                item.setStatus(true);
//                refresh();
//                break;
//            }
//        }
    }

    public void loading_on() {
        binding.progress.setVisibility(View.VISIBLE);
        binding.recycleMqttBrokers.setVisibility(View.GONE);
    }

    public void loading_off() {
        binding.progress.setVisibility(View.GONE);
        binding.recycleMqttBrokers.setVisibility(View.VISIBLE);
    }

    @Override
    public void getlist(ArrayList<wifi> list) {
        this.list = list;
        refresh();
        loading_off();

    }

    @Override
    public void disconnect(String name) {
        disconnect();
    }

    @Override
    public void changestate(Boolean state) {
        if (state) {
            binding.relativeLayoutPortActivity.setVisibility(View.VISIBLE);
        } else
            binding.relativeLayoutPortActivity.setVisibility(View.GONE);

    }

    @Override
    public void success_wifi_connect(String ssid) {
        try {
            JSONObject item = new JSONObject(ssid);
            ssid = item.getString("networkName");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (wifi item : list) {
            item.status=false;
            if (item.name.equals(ssid)) {
                item.status = true;
                break;
            }
        }
        refresh();
        loading_off();
    }

    @Override
    public void failed_wifi_connect(String error) {
        loading_off();
        Toast.makeText(xcs, error, Toast.LENGTH_SHORT).show();
        if (dialog_connect != null)
            dialog_connect.show();
        else
            Toast.makeText(xcs, "Error to establish thr connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success_wifi_disconnect() {
        loading_off();
    }

    @Override
    public void failed_wifi_disconnect(String error) {
        loading_off();
        Toast.makeText(xcs, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void get_wifi_config() {
        for (wifi item : list) {
            if (item.status) {
                viewmodel.get_wifi_config(item);
                break;
            }
        }
    }

    public boolean checkconnection(String netname) {
        for (wifi item : list) {
            if (item.name.equals(netname)) {
                if (item.status) {
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

    @Override
    public void show_wifi_config(ConfigWifi result) {
        dialog_connect = new Dialog(this);
        dialog_connect.setContentView(R.layout.dialogwifi_detaile);
        EditText editIP = (EditText) dialog_connect.findViewById(R.id.editIP);
        EditText editMask = (EditText) dialog_connect.findViewById(R.id.editMask);
        EditText editGateway = (EditText) dialog_connect.findViewById(R.id.editGateway);
        EditText editDNS = (EditText) dialog_connect.findViewById(R.id.editDNS);
        CheckBox ckAuoConnect = (CheckBox) dialog_connect.findViewById(R.id.ckAuoConnect);
        CheckBox ckDHCP = (CheckBox) dialog_connect.findViewById(R.id.ckDHCP);
        Button relsave = (Button) dialog_connect.findViewById(R.id.relsave);
        Button relexit = (Button) dialog_connect.findViewById(R.id.relexit);

        editIP.setText(result.getIp());
        editMask.setText(result.getMask());
        editGateway.setText(result.getGateway());
        editDNS.setText(result.getDns());

        List<WifiSetting> list = wifidb.all();
        for (WifiSetting item : list) {
            if (checkconnection(item.getNemtworkName())) {
                if (item.getAutoConnect()) {
                    ckAuoConnect.setChecked(true);
                }
                if (item.getDhcp())
                    ckDHCP.setChecked(true);
            }
        }


        relsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<WifiSetting> list = wifidb.all();
                for (WifiSetting item : list) {
                    item.setAutoConnect(false);
                    item.setDhcp(false);
                    db.update(item);
                    if (checkconnection(item.getNemtworkName())) {
                        if (ckAuoConnect.isChecked())
                            item.setAutoConnect(true);
                        if (ckDHCP.isChecked())
                            item.setDhcp(true);
                        db.update(item);
                        break;
                    }
                }
                viewmodel.savewificonfig(editIP.getText().toString(), editMask.getText().toString(), editGateway.getText().toString(), ckDHCP.isChecked(), editDNS.getText().toString());
                dialog_connect.dismiss();
            }
        });
        relexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_connect.dismiss();
            }
        });

        dialog_connect.show();
    }


}
