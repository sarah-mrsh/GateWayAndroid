package com.example.fanp.presentation.modbus;

import android.os.AsyncTask;
import android.util.Log;

//import com.example.fanp.Mo;
import com.example.fanp.CommandResponce;
import com.example.fanp.FunctionCode;
import com.example.fanp.ModbusConfig;
import com.example.fanp.ModbusConfigResponse;
import com.example.fanp.ModbusDataType;
import com.example.fanp.SystemModbusConfigGrpc;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;
import io.grpc.ManagedChannel;

public class GrpcModBus {


    ManagedChannel channel;
    I4AllSettingDao db;

    @Inject
    public GrpcModBus(ManagedChannel channel, I4AllSettingDao db) {
        this.channel = channel;
        this.db = db;
    }


    public void update_data() {
        //TCP UPDATE
        new modbustcp().execute();

    }

    private class modbustcp extends AsyncTask<Void, Void, ModbusConfigResponse> {

        private modbustcp() {
        }

        @Override
        protected ModbusConfigResponse doInBackground(Void... voids) {
            try {
                SystemModbusConfigGrpc.SystemModbusConfigBlockingStub stub = SystemModbusConfigGrpc.newBlockingStub(channel);

                ModbusConfig.Builder config = ModbusConfig.newBuilder();
                ModbusConfig.ModbusDevice.Builder moddevice = ModbusConfig.ModbusDevice.newBuilder();
                moddevice.setRtuOrTcp(true);
//
//                //TCP
                I4AllSetting data = db.gettcpmodbus();
                if (data != null) {
                    JSONArray array = new JSONArray(data.getItemsData());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject item = array.getJSONObject(i);
                        ModbusConfig.ModbusDevice.ModbusTCPSlaves modbusTCPSlaves =
                                ModbusConfig.ModbusDevice.ModbusTCPSlaves.newBuilder().setDeviceId(item.getString("deviceid"))
                                .setDeviceName(item.getString("devicename"))
                                .setIP(item.getString("ip"))
                                .setPort(Integer.parseInt(item.getString("port"))).build();
                        moddevice.setModbusTCPSlaves(modbusTCPSlaves);
                        int deviceid = item.getInt("deviceid");
                        List<I4AllSetting> tcptags = db.getitembyitesref(deviceid);
                        for (I4AllSetting tag:tcptags){
                            JSONObject tagitem = new JSONObject(tag.getItemsData());
                            ModbusConfig.ModbusDevice.ModbusTag.Builder taggr =  ModbusConfig.ModbusDevice.ModbusTag.newBuilder();
                            taggr.setTagName(tagitem.getString("tagname"));
                            taggr.setTagId(tagitem.getString("tagname"));
                            taggr.setServerId(tagitem.getString("tagname"));
                            taggr.setIntervalTime(tagitem.getInt("interval"));

                            switch (tagitem.getString("datatype")){
                                case "MBOOLEAN" :taggr.setDataType(ModbusDataType.MBOOLEAN );break;
                                case "MINT8" :taggr.setDataType(ModbusDataType.MINT8);break;
                                case "MUINT8" :taggr.setDataType(ModbusDataType.MUINT8);break;
                                case "MINT16" :taggr.setDataType(ModbusDataType.MINT16);break;
                                case "MUINT16" :taggr.setDataType(ModbusDataType.MUINT16);break;
                                case "MINT32" :taggr.setDataType(ModbusDataType.MINT32);break;
                                case "MUINT32" :taggr.setDataType(ModbusDataType.MUINT32);break;
                                case "MINT64" :taggr.setDataType(ModbusDataType.MINT64);break;
                                case "MUINT64" :taggr.setDataType(ModbusDataType.MUINT64);break;
                                case "MFLOAT32" :taggr.setDataType(ModbusDataType.MFLOAT32);break;
                                case "MFLOAT64" :taggr.setDataType(ModbusDataType.MFLOAT64);break;
                                case "MSTRING" :taggr.setDataType(ModbusDataType.MSTRING);break;
                            }
                            switch (tagitem.getString("functioncode")){
                                case "RRAD_COIL" : taggr.setFunctionCode(FunctionCode.RRAD_COIL);break;
                                case "READ_DISCRETE_INPUTS" : taggr.setFunctionCode(FunctionCode.READ_DISCRETE_INPUTS);break;
                                case "READ_MULTIPLE_HOLDING_REGISTERS" : taggr.setFunctionCode(FunctionCode.READ_MULTIPLE_HOLDING_REGISTERS);break;
                                case "READ_INPUT_REGISTERS" : taggr.setFunctionCode(FunctionCode.READ_INPUT_REGISTERS);break;
                                case "WRITE_SINGLE_COIL" : taggr.setFunctionCode(FunctionCode.WRITE_SINGLE_COIL);break;
                                case "WRITE_SINGLE_HOLDING_REGISTER" : taggr.setFunctionCode(FunctionCode.WRITE_SINGLE_HOLDING_REGISTER);break;
                                case "WRITE_MULTIPLE_COILS" : taggr.setFunctionCode(FunctionCode.WRITE_MULTIPLE_COILS);break;
                                case "WRITE_MULTIPLE_HLDING_REGISTERS" : taggr.setFunctionCode(FunctionCode.WRITE_MULTIPLE_HLDING_REGISTERS);break;
                            }
                            config.addModbusDevice(moddevice);
                        }

                    }
                    ModbusConfigResponse replay = stub.sendSystemModbusConfig(config.build());
                }


//                SystemCommandGrpc.SystemCommandBlockingStub stub = SystemCommandGrpc.newBlockingStub(channel);
//                Command request = Command.newBuilder().setCommandID(0).build();
//                CommandResponce reply = stub.sendSystemCommand(request);
//                Log.e("APP", "getMessage");

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

//    private class modbusrtu extends AsyncTask<Void, Void, ModbusConfigResponse> {
//
//        private modbusrtu() {
//        }
//
//        @Override
//        protected ModbusConfigResponse doInBackground(Void... voids) {
//            try {
//                SystemModbusConfigGrpc.SystemModbusConfigBlockingStub stub = SystemModbusConfigGrpc.newBlockingStub(channel);
//
//                ModbusConfig.Builder config = ModbusConfig.newBuilder();
//                config.setRtuOrTcp(true);
//
//                //TCP
//                I4AllSetting data = db.gettcpmodbus();
//                if (data != null) {
//                    JSONArray array = new JSONArray(data.getItemsData());
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject item = array.getJSONObject(i);
//                        ModbusConfig.ModbusTCPSlaves modbusTCPSlaves =
//                                ModbusConfig.ModbusTCPSlaves.newBuilder().setDeviceid(item.getString("deviceid"))
//                                        .setDevicename(item.getString("devicename"))
//                                        .setIP(item.getString("ip"))
//                                        .setPort(Integer.parseInt(item.getString("port"))).build();
//                        config.setModbusTCPSlaves(modbusTCPSlaves);
//                        int deviceid = item.getInt("deviceid");
//                        List<I4AllSetting> tcptags = db.getitembyitesref(deviceid);
//                        for (I4AllSetting tag:tcptags){
//                            JSONObject tagitem = new JSONObject(tag.getItemsData());
//                            ModbusConfig.ModbusTag.Builder taggr =  ModbusConfig.ModbusTag.newBuilder();
//                            taggr.setTagName(tagitem.getString("tagname"));
//                            taggr.setTagId(tagitem.getString("tagname"));
//                            taggr.setServerId(tagitem.getString("tagname"));
//                            taggr.setIntervalTime(tagitem.getInt("interval"));
//
//                            switch (tagitem.getString("datatype")){
//                                case "MBOOLEAN" :taggr.setDataType(ModbusDataType.MBOOLEAN );break;
//                                case "MINT8" :taggr.setDataType(ModbusDataType.MINT8);break;
//                                case "MUINT8" :taggr.setDataType(ModbusDataType.MUINT8);break;
//                                case "MINT16" :taggr.setDataType(ModbusDataType.MINT16);break;
//                                case "MUINT16" :taggr.setDataType(ModbusDataType.MUINT16);break;
//                                case "MINT32" :taggr.setDataType(ModbusDataType.MINT32);break;
//                                case "MUINT32" :taggr.setDataType(ModbusDataType.MUINT32);break;
//                                case "MINT64" :taggr.setDataType(ModbusDataType.MINT64);break;
//                                case "MUINT64" :taggr.setDataType(ModbusDataType.MUINT64);break;
//                                case "MFLOAT32" :taggr.setDataType(ModbusDataType.MFLOAT32);break;
//                                case "MFLOAT64" :taggr.setDataType(ModbusDataType.MFLOAT64);break;
//                                case "MSTRING" :taggr.setDataType(ModbusDataType.MSTRING);break;
//                            }
//                            switch (tagitem.getString("functioncode")){
//                                case "RRAD_COIL" : taggr.setFunctionCode(FunctionCode.RRAD_COIL);break;
//                                case "READ_DISCRETE_INPUTS" : taggr.setFunctionCode(FunctionCode.READ_DISCRETE_INPUTS);break;
//                                case "READ_MULTIPLE_HOLDING_REGISTERS" : taggr.setFunctionCode(FunctionCode.READ_MULTIPLE_HOLDING_REGISTERS);break;
//                                case "READ_INPUT_REGISTERS" : taggr.setFunctionCode(FunctionCode.READ_INPUT_REGISTERS);break;
//                                case "WRITE_SINGLE_COIL" : taggr.setFunctionCode(FunctionCode.WRITE_SINGLE_COIL);break;
//                                case "WRITE_SINGLE_HOLDING_REGISTER" : taggr.setFunctionCode(FunctionCode.WRITE_SINGLE_HOLDING_REGISTER);break;
//                                case "WRITE_MULTIPLE_COILS" : taggr.setFunctionCode(FunctionCode.WRITE_MULTIPLE_COILS);break;
//                                case "WRITE_MULTIPLE_HLDING_REGISTERS" : taggr.setFunctionCode(FunctionCode.WRITE_MULTIPLE_HLDING_REGISTERS);break;
//                            }
//                            config.addModbusTag(taggr);
//                        }
//                        ModbusConfigResponse replay = stub.sendSystemModbusConfig(config.build());
//                    }
//                }
//
//
////                SystemCommandGrpc.SystemCommandBlockingStub stub = SystemCommandGrpc.newBlockingStub(channel);
////                Command request = Command.newBuilder().setCommandID(0).build();
////                CommandResponce reply = stub.sendSystemCommand(request);
////                Log.e("APP", "getMessage");
//
//                return null;
//            } catch (Exception e) {
//                Log.e("APP", "Exception getMessage");
//                StringWriter sw = new StringWriter();
//                PrintWriter pw = new PrintWriter(sw);
//                e.printStackTrace(pw);
//                pw.flush();
//                return null;
//            }
//        }
//
//
//        @Override
//        protected void onPostExecute(ModbusConfigResponse result) {
//            Log.e("APP", "onPostExecute");
//        }
//    }

}
