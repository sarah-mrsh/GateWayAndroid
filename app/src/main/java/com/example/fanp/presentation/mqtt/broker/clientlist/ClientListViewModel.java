package com.example.fanp.presentation.mqtt.broker.clientlist;

import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.lifecycle.ViewModel;

import com.example.fanp.R;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class ClientListViewModel extends ViewModel {

    @Inject
    I4AllSettingDao db;

    ClientList main;

    @Inject
    public ClientListViewModel(){}


    public void finish(){
        main.finish();
    }



    public void add(){

        final Dialog dialog = new Dialog(main);
        dialog.setContentView(R.layout.add_client);

        dialog.findViewById(R.id.btnadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();
                EditText edtname = (EditText)dialog.findViewById(R.id.edtname);
                EditText edtclientid = (EditText)dialog.findViewById(R.id.edtclientid);
                Spinner spnumber = (Spinner)dialog.findViewById(R.id.spnumber);
                Spinner spsubpub = (Spinner)dialog.findViewById(R.id.sppubsub);

                try {
                    object.put("clientname", edtname.getText().toString());
                    object.put("clientid", edtclientid.getText().toString());
                    object.put("qos", spnumber.getSelectedItem().toString());
                    object.put("op", spsubpub.getSelectedItem().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                I4AllSetting data = new I4AllSetting(0, 502, object.toString(), false, timeStamp);
                db.insert(data);
                dialog.dismiss();
                main.refresh();
            }
        });
        dialog.show();


    }
}
