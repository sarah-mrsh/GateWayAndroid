package com.example.fanp.presentation.mqtt.broker.taglist;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class LocalBrokerTagViewModel extends ViewModel {

    @Inject
    Context ctx;

    @SuppressLint("StaticFieldLeak")
    public Localbrokertaglist main;


    @Inject
    I4AllSettingDao db;


    @Inject
    public LocalBrokerTagViewModel() {
    }

    public void finish(){
        main.finish();
    }

    public void add() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(main);
//        View dialogView = LayoutInflater.from(main).inflate(R.layout.add_broker_tag, null, false);
//        builder.setView(dialogView);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
        final Dialog dialog = new Dialog(main);
        dialog.setContentView(R.layout.add_broker_tag);

        EditText edt_name = (EditText) dialog.findViewById(R.id.edt_name);
        EditText edt_topic = (EditText) dialog.findViewById(R.id.edt_topic);
        EditText edt_schedule = (EditText) dialog.findViewById(R.id.edt_schedule);
        EditText edt_des = (EditText) dialog.findViewById(R.id.edt_des);
        Spinner sp_type = (Spinner) dialog.findViewById(R.id.sp_type);
        CheckBox chb_system = (CheckBox) dialog.findViewById(R.id.chb_system);
        Button btnadd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnexit = (Button) dialog.findViewById(R.id.btnexit);

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
                main.refresh();
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    public void edit(){

    }


}
