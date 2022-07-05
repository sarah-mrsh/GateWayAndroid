package com.example.fanp.presentation.modbus;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

//import com.example.fanp.Mo;
import com.example.fanp.BaudRate;
import com.example.fanp.CommandResponce;
import com.example.fanp.FunctionCode;
import com.example.fanp.ModbusConfig;
import com.example.fanp.ModbusConfigResponse;
import com.example.fanp.ModbusDataType;
import com.example.fanp.Parity;
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
        new modbusrtu().execute();

    }

    private class modbustcp extends AsyncTask<Void, Void, ModbusConfigResponse> {

        private modbustcp() {
        }

        @Override
        protected ModbusConfigResponse doInBackground(Void... voids) {
            try {
                SystemModbusConfigGrpc.SystemModbusConfigBlockingStub stub = SystemModbusConfigGrpc.newBlockingStub(channel);

                ModbusConfig.Builder config = ModbusConfig.newBuilder();

                //TCP
                I4AllSetting data = db.gettcpmodbus();
                if (data != null) {
                    JSONArray array = new JSONArray(data.getItemsData());//[{"devicename":"ali","deviceid":"985","ip":"192","port":"185"}]
                    for (int i = 0; i < array.length(); i++) {
                        ModbusConfig.ModbusDevice.Builder moddevice = ModbusConfig.ModbusDevice.newBuilder();
                        moddevice.setRtuOrTcp(true);
                        JSONObject item = array.getJSONObject(i);
                        int deviceid = item.getInt("deviceid");
                        List<I4AllSetting> tcptags = db.getitembyitesref(deviceid);
                        if (tcptags.size()==0)
                            continue;

                        ModbusConfig.ModbusDevice.ModbusTCPSlaves modbusTCPSlaves =
                                ModbusConfig.ModbusDevice.ModbusTCPSlaves.newBuilder().setClientID(item.getString("deviceid"))
                                .setClientName(item.getString("devicename"))
                                .setIP(item.getString("ip"))
                                .setPort(Integer.parseInt(item.getString("port"))).build();
                        moddevice.setModbusTCPSlaves(modbusTCPSlaves);
                        for (I4AllSetting tag:tcptags){
                            JSONObject tagitem = new JSONObject(tag.getItemsData());
                            ModbusConfig.ModbusDevice.ModbusTag.Builder taggr =  ModbusConfig.ModbusDevice.ModbusTag.newBuilder();
                            taggr.setTagName(tagitem.getString("tagname"));
                            taggr.setTagId(tagitem.getString("tagid"));
                            taggr.setServerId(tagitem.getString("serverid"));
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
                            moddevice.addModbusTag(taggr);
                        }
                        config.addModbusDevice(moddevice);
                    }
                }


                // MODBUS RTU




                ModbusConfigResponse replay = stub.sendSystemModbusConfig(config.build());
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




    private class modbusrtu extends AsyncTask<Void, Void, ModbusConfigResponse> {

        private modbusrtu() {
        }

        @Override
        protected ModbusConfigResponse doInBackground(Void... voids) {
            try {
                SystemModbusConfigGrpc.SystemModbusConfigBlockingStub stub = SystemModbusConfigGrpc.newBlockingStub(channel);

                ModbusConfig.Builder config = ModbusConfig.newBuilder();

                //TCP
                I4AllSetting data = db.getrtumodbusserverlist();
                if (data != null) {
                    JSONArray array = new JSONArray(data.getItemsData());//[{"devicename":"ali","deviceid":"985","ip":"192","port":"185"}]
                    for (int i = 0; i < array.length(); i++) {
                        ModbusConfig.ModbusDevice.Builder moddevice = ModbusConfig.ModbusDevice.newBuilder();
                        moddevice.setRtuOrTcp(false);
                        JSONObject item = array.getJSONObject(i);
                        int deviceid = item.getInt("deviceid");
                        List<I4AllSetting> tcptags = db.getitembyitesref(deviceid);
                        if (tcptags.size()==0)
                            continue;

                        I4AllSetting basedata = db.getitembyId(512);
                        JSONObject object = new JSONObject(basedata.getItemsData());



                        ModbusConfig.ModbusDevice.ModbusRTUSlaves.Builder modbusTCPSlaves =
                                ModbusConfig.ModbusDevice.ModbusRTUSlaves.newBuilder();

                        modbusTCPSlaves.setClientID(item.getString("deviceid")).setStartBit(object.getInt("startbit")).setStopBit(object.getInt("endbit")).setDataBit(object.getInt("databit"))
                                .setClientName(item.getString("devicename"));

                        switch (object.getString("baudrate")){
                            case "B110" : modbusTCPSlaves.setBaudRate(BaudRate.B110);break;
                            case "B300" : modbusTCPSlaves.setBaudRate(BaudRate.B300);break;
                            case "B600" : modbusTCPSlaves.setBaudRate(BaudRate.B600);break;
                            case "B1200" : modbusTCPSlaves.setBaudRate(BaudRate.B1200);break;
                            case "B2400" : modbusTCPSlaves.setBaudRate(BaudRate.B2400);break;
                            case "B4800" : modbusTCPSlaves.setBaudRate(BaudRate.B4800);break;
                            case "B9600" : modbusTCPSlaves.setBaudRate(BaudRate.B9600);break;
                            case "B14400" : modbusTCPSlaves.setBaudRate(BaudRate.B14400);break;
                            case "B19200" : modbusTCPSlaves.setBaudRate(BaudRate.B19200);break;
                            case "B38400" : modbusTCPSlaves.setBaudRate(BaudRate.B38400);break;
                            case "B56000" : modbusTCPSlaves.setBaudRate(BaudRate.B56000);break;
                            case "B57600" : modbusTCPSlaves.setBaudRate(BaudRate.B57600);break;
                            case "B115200" : modbusTCPSlaves.setBaudRate(BaudRate.B115200);break;
                            case "B125000" : modbusTCPSlaves.setBaudRate(BaudRate.B125000);break;
                            case "B256000" : modbusTCPSlaves.setBaudRate(BaudRate.B256000);break;
                        }
                        switch (object.getString("parity")){
                            case "ODD": modbusTCPSlaves.setParity(Parity.ODD);break;
                            case "EVEN": modbusTCPSlaves.setParity(Parity.EVEN);break;
                            case "NONE": modbusTCPSlaves.setParity(Parity.NONE);break;
                        }





                        moddevice.setModbusRTUSlaves(modbusTCPSlaves);
                        for (I4AllSetting tag:tcptags){
                            JSONObject tagitem = new JSONObject(tag.getItemsData());
                            ModbusConfig.ModbusDevice.ModbusTag.Builder taggr =  ModbusConfig.ModbusDevice.ModbusTag.newBuilder();
                            taggr.setTagName(tagitem.getString("tagname"));
                            taggr.setTagId(tagitem.getString("tagid"));
                            taggr.setServerId("154");
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

                            taggr.setStartingAddress(tagitem.getInt("modbusaddres"));
                            taggr.setNumberOfRegisters(0);




                            moddevice.addModbusTag(taggr);
                        }
                        config.addModbusDevice(moddevice);
                    }
                }


                // MODBUS RTU




                ModbusConfigResponse replay = stub.sendSystemModbusConfig(config.build());
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
}
