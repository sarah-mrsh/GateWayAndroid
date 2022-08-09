package com.example.fanp.presentation.convertprotocol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.ConfigConvertProtocol;
import com.example.fanp.ConvertProtocolConfigResponse;
import com.example.fanp.ModbusConfigResponse;
import com.example.fanp.R;
import com.example.fanp.SystemConvertProtocolConfigGrpc;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.utils.BasicFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.grpc.ManagedChannel;

public class ConvertProtocol extends BasicFragment {


    public static boolean first = true;

    @Inject
    I4AllSettingDao db;

    @Inject
    ManagedChannel channel;


    List<TagModelSP> list = new ArrayList<>();
    List<I4AllSetting> data = new ArrayList<>();
    List<I4AllSetting> temp = new ArrayList<>();


    AdapterConvertProtol adapter;
    RecyclerView recyclerView;
    ImageView imgaddfree;

    @Inject
    public ConvertProtocol() {
    }


    public void setdata() {
        if (!first) {

            data = db.getconvertprotocols();
            imgaddfree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.add(new I4AllSetting(0, 640, "", false, ""));
                    force_update();
                }
            });
            //Now specific components here (you can initialize Buttons etc)
        } else
            update();
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convertprotocol, parent, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recrtu);
        Button btnsave = (Button) view.findViewById(R.id.btnsave);
        imgaddfree = (ImageView) view.findViewById(R.id.imgaddfree);
        data = db.getconvertprotocols();
        imgaddfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(new I4AllSetting(0, 640, "", false, ""));
                force_update();
            }
        });
        //Now specific components here (you can initialize Buttons etc)

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        setdata();
        return view;
    }

    public void reset() {
        for (I4AllSetting item : data) {
            db.delete(item);
        }
    }

    public boolean isdub(I4AllSetting item) {
        try {
            for (int i = 0; i < data.size(); i++) {
                JSONObject object = new JSONObject(data.get(i).getItemsData());
                JSONObject target = new JSONObject(item.getItemsData());
                if (object.getString("from").equals(target.getString("from")) && object.getString("to").equals(target.getString("to"))) {
                    return true;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void send() {

    }

    public void save() {
        try {

            for (int i = 0; i <= adapter.getItemCount(); i++) {
                if (recyclerView.findViewHolderForAdapterPosition(i) != null) {
                    JSONObject object = new JSONObject();
                    Spinner sptar = (Spinner) recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.sptar);
                    Spinner spdest = (Spinner) recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.spdest);
                    object.put("from", sptar.getSelectedItem().toString());
                    object.put("to", spdest.getSelectedItem().toString());
                    if (sptar.getSelectedItem().toString().equals("Choise") || spdest.getSelectedItem().toString().equals("Choise")) {
                        continue;
                    }
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                    I4AllSetting newdata = new I4AllSetting(0, 640, object.toString(), false, timeStamp);
                    if (!isdub(newdata))
                        db.insert(newdata);
                }
            }


            update();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
        new convertgrpc().execute();
    }

    public void refresh() {
        list.clear();
        list.add(new TagModelSP("Choise", "Choise", "Choise"));
        try {
            // MODBUS TCP
            if (db.gettcpmodbus() != null) {
                I4AllSetting modbus_tcp = db.gettcpmodbus();
                JSONArray tcparray = new JSONArray(modbus_tcp.getItemsData());
                for (int i = 0; i < tcparray.length(); i++) {
                    int deviceid = tcparray.getJSONObject(i).getInt("deviceid");
                    List<I4AllSetting> data = db.getitembyitesref(deviceid);
                    for (I4AllSetting item : data) {
                        JSONObject temp = new JSONObject(item.getItemsData());
                        list.add(new TagModelSP(temp.getString("tagname"), temp.getString("tagid"), "MODBUSTCP"));
                    }
                }
            }

// MODBUS RTU
            if (db.getrtumodbusserverlist() != null) {

                I4AllSetting modbus_rtu = db.getrtumodbusserverlist();
                JSONArray rtuarray = new JSONArray(modbus_rtu.getItemsData());
                for (int i = 0; i < rtuarray.length(); i++) {
                    int deviceid = rtuarray.getJSONObject(i).getInt("deviceid");
                    List<I4AllSetting> data = db.getitembyitesref(deviceid);
                    for (I4AllSetting item : data) {
                        JSONObject temp = new JSONObject(item.getItemsData());
                        list.add(new TagModelSP(temp.getString("tagname"), temp.getString("tagid"), "MODBUSTCP"));
                    }
                }
            }

            // MODBUS RTU
//            List<I4AllSetting> modbus_rtu = db.getitembyitesref(513);
//            for (int i = 0; i < modbus_rtu.size(); i++) {
//                int deviceid = new JSONObject(modbus_rtu.get(i).getItemsData()).getInt("deviceid");
//                List<I4AllSetting> data = db.getitembyitesref(deviceid);
//                for (I4AllSetting item : data) {
//                    JSONObject temp = new JSONObject(item.getItemsData());
//                    list.add(new TagModelSP(temp.getString("tagname"), temp.getString("tagid"), "MODBUS RTU"));
//                }
//            }

            // MQTT CLIENT
            if (db.getitembyitesref(517) != null) {
                List<I4AllSetting> mqtt_client = db.getitembyitesref(517);
                for (int i = 0; i < mqtt_client.size(); i++) {
                    int deviceid = new JSONObject(mqtt_client.get(i).getItemsData()).getInt("clientid");
                    List<I4AllSetting> data = db.getitembyitesref(deviceid);
                    for (I4AllSetting item : data) {
                        JSONObject temp = new JSONObject(item.getItemsData());
                        list.add(new TagModelSP(temp.getString("tagname"), temp.getString("tagid"), "MQTT CLIENT"));
                    }
                }
            }

            // S7
            if (db.getplc() != null) {
                List<I4AllSetting> s7 = db.getplc();
                for (I4AllSetting item : s7) {
                    JSONObject temp = new JSONObject(item.getItemsData());
                    List<I4AllSetting> tags = db.getitembyitesref(Integer.parseInt(temp.getString("deviceid")));
                    for (I4AllSetting data : tags) {
                        JSONObject object = new JSONObject(data.getItemsData());
                        if (!object.has("iotype")) {
                            list.add(new TagModelSP(temp.getString("devicename"), temp.getString("deviceid"), "S7 DATABLOCK CLIENT"));
                        } else {
                            list.add(new TagModelSP(temp.getString("devicename"), temp.getString("deviceid"), "S7 IO CLIENT"));
                        }
                    }
                }
            }


            force_update();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void force_update() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new AdapterConvertProtol(list, getContext(), data, db);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void update() {
//
//        data.add(new I4AllSetting(0, 640, "", false, ""));
        data = db.getconvertprotocols();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new AdapterConvertProtol(list, getContext(), data, db);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }


    private class convertgrpc extends AsyncTask<Void, Void, ModbusConfigResponse> {
        List<I4AllSetting> alldata = new ArrayList<>();

        private convertgrpc() {
        }

        @Override
        protected ModbusConfigResponse doInBackground(Void... voids) {
            try {
                SystemConvertProtocolConfigGrpc.SystemConvertProtocolConfigBlockingStub stub = SystemConvertProtocolConfigGrpc.newBlockingStub(channel);

                ConfigConvertProtocol.Builder config = ConfigConvertProtocol.newBuilder();
                alldata = db.getconvertprotocols();
                for (int i = 0; alldata.size() > i; i++) {
                    JSONObject object = new JSONObject(alldata.get(i).getItemsData());
                    ConfigConvertProtocol.ConvertProtocol.Builder convert = ConfigConvertProtocol.ConvertProtocol.newBuilder();
                    convert.setTagNameSource(object.getString("from"));
                    convert.setTagNameDist(object.getString("to"));
                    convert.setTimer(500);
                    config.addConvertProtocol(convert);
                }
                ConvertProtocolConfigResponse replay = stub.sendSystemConvertProtocolConfig(config.build());
                return null;
            } catch (Exception e) {
                Log.e("APP", "Exception getMessage");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                pw.flush();
                return null;
            }
        }


        @Override
        protected void onPostExecute(ModbusConfigResponse result) {
            Log.e("APP", "onPostExecute");
        }
    }
}
