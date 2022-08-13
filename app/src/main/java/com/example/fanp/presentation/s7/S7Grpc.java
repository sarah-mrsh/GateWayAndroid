package com.example.fanp.presentation.s7;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fanp.ConfigResponse;
import com.example.fanp.ConfigSnapSeven;
import com.example.fanp.ConfigWifi;
import com.example.fanp.ParameterDataType;
import com.example.fanp.SnapSevenConfigResponse;
import com.example.fanp.SystemSnapSevenConfigGrpc;
import com.example.fanp.SystemWifiConfigGrpc;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.wifi;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;

import io.grpc.ManagedChannel;

public class S7Grpc {

    ManagedChannel channel;
    I4AllSettingDao db;

    @Inject
    public S7Grpc(ManagedChannel channel, I4AllSettingDao db) {
        this.channel = channel;
        this.db = db;
    }

    public void update() {
        new s7update().execute();
    }


    private class s7update extends AsyncTask<wifi, Void, SnapSevenConfigResponse> {


        String target;

        private s7update() {
        }

        @Override
        protected SnapSevenConfigResponse doInBackground(wifi... net) {
            try {
                SystemSnapSevenConfigGrpc.SystemSnapSevenConfigBlockingStub stub = SystemSnapSevenConfigGrpc.newBlockingStub(channel);
                ConfigSnapSeven.Builder config = ConfigSnapSeven.newBuilder();

                List<I4AllSetting> data = db.getplc();
                for (int i = 0; i < data.size(); i++) {
                    ConfigSnapSeven.PLCConfig.Builder plc = ConfigSnapSeven.PLCConfig.newBuilder();
                    JSONObject object = new JSONObject(data.get(i).getItemsData());
                    plc.setPlcID(object.getString("deviceid"));
                    plc.setPlcName(object.getString("devicename"));
                    plc.setPlcPort(Integer.parseInt(object.getString("port")));
                    plc.setPlcIp(object.getString("ip"));
                    plc.setPlcAddress(object.getString("ip"));


                    //FILL IO TAG
                    List<I4AllSetting> items = db.getitembyitesref(Integer.parseInt(object.getString("deviceid")));
                    for (I4AllSetting dataitem : items) {
                        try {
                            JSONObject tag = new JSONObject(dataitem.getItemsData());
                            if (tag.has("iotype")) {// its io tag
                                ConfigSnapSeven.PLCConfig.IoTag.Builder iotag = ConfigSnapSeven.PLCConfig.IoTag.newBuilder();
                                iotag.setTagName(tag.getString("tagname"));
                                iotag.setTagId(tag.getString("tagid"));
                                iotag.setBitCount(tag.getInt("bitcount"));
                                iotag.setByteCount(tag.getInt("bytecount"));
                                iotag.setIoType(tag.getString("iotype"));
                                iotag.setTagType(tag.getString("tagtype"));
//                                iotag.setWordCount(tag.getInt("wordcount"));
                                plc.addIotag(iotag);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    // end fill io tag


                    //Fill Data block
// {"tagname":"TAG","tagid":"015448","parameter_id":"525748","functionblock_id":"8541","dbid":"012619","function":"(1) Read Coil","datablockcount":"4185",
// "description":"4185","name":"PARAN1","type":"BOOLEAN","address":"8541","bitnumber":"8541","parameterid":"525748","paramdescription":"8541"}

                    List<I4AllSetting> datablocks = db.getitembyitesref(630);
                    for (int k = 0; k < datablocks.size(); k++) {
                        JSONObject object1 = new JSONObject(datablocks.get(k).getItemsData());
                        ConfigSnapSeven.PLCConfig.DataBlockTag.Builder iotag = ConfigSnapSeven.PLCConfig.DataBlockTag.newBuilder();
                        iotag.setTagName(object1.getString("tagname"));
                        iotag.setTagId(object1.getString("tagid"));
                        iotag.setFunctionCode(object1.getString("function"));
                        iotag.setCount(0);
                        switch (object1.getString("type")){
                            case "FLOAT32": iotag.setParameter(ParameterDataType.FLOAT32); break;
                        }
                        iotag.setStartAddress(object1.getInt("address"));
                        iotag.setBitNumber(object1.getInt("bitnumber"));
                        iotag.setDbNumber(object1.getInt("datablockcount"));


                        plc.addDatablocktag(iotag);
                    }
                    config.addPLCConfig(plc);

                }

                SnapSevenConfigResponse replay = stub.sendSystemSnapSevenConfig(config.build());
                Log.e("APP", "getMessage");

                return replay;
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
        protected void onPostExecute(SnapSevenConfigResponse result) {
            Log.e("APP", "S7 Data Updated");

        }
    }


}
