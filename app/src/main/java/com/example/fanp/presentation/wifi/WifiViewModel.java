package com.example.fanp.presentation.wifi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.Command;
import com.example.fanp.CommandResponce;
import com.example.fanp.CommandType;
import com.example.fanp.ConfigWifi;
import com.example.fanp.MqttClients;
import com.example.fanp.MqttClientsConfigGrpc;
import com.example.fanp.R;
import com.example.fanp.SystemCommandGrpc;
import com.example.fanp.SystemWifiConfigGrpc;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.data.WifiDao;
import com.example.fanp.domain.local.repository.WifiSetting;
import com.example.fanp.presentation.wifi.savednetworks.SavedNetworks;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.grpc.ManagedChannel;

public class WifiViewModel extends ViewModel {

//    String wifiList[] = {"Hasan blue", "Barobach", "zapata", "Portugle", "Hidden", "googooli"};
//    int icons[] = {R.drawable.wifi_icon, R.drawable.wifi_icon, R.drawable.wifi_icon, R.drawable.wifi_icon, R.drawable.wifi_icon, R.drawable.wifi_icon};


    AdapterWifiImpl listener;

    @Inject
    I4AllSettingDao db;

    @Inject
    Context ctx;

    @Inject
    ManagedChannel channel;

    @Inject
    WifiDao wifidb;


    public String ip_wifi;
    public String mask;
    public String gateway;
    public String dns;
    public boolean autoConnect;
    public boolean dhcp;
    public boolean active = true;


    @Inject
    public WifiViewModel() {
    }

    public void back() {
        Log.i("back", "go back");
    }


    public void getwifilist() {
        new WifiList().execute();
    }


    public void disconnect(String name) {
        JSONObject data = new JSONObject();
        try {
            data.put("networkName", name);
            new wifidisconnect().execute(data.toString());
        } catch (JSONException e) {
            //TODO EXCEPTION
        }
    }

    public void setwifionoff(boolean state) {
        new wifistate().execute(state);
    }


    public void connect(String name, String pass) {
        try {
//            JSONObject object = new JSONObject();
//            object.put("command", "wifiConnect");
//            object.put("commandID", 2);
            JSONObject data = new JSONObject();
            data.put("networkName", name);
            data.put("networkPassword", pass);
//            object.put("data", data);

            new connetwifi().execute(data.toString());


        } catch (JSONException e) {
            //TODO EXCEPTION
        }
    }


    public void wifilist() {
        new WifiList().execute();
    }


    public void get_wifi_config(wifi model) {
        new get_config().execute(model);
    }

    public void savewificonfig(String ip, String mask, String gateway, boolean dhcp, String dns) {
        //todo
        JSONObject object = new JSONObject();
        try {
            object.put("ip", ip);
            object.put("mask", mask);
            object.put("gateway", gateway);
            object.put("dhcp", dhcp);
            object.put("dns", dns);
            new save_config().execute(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private class save_config extends AsyncTask<JSONObject, Void, ConfigWifi> {


        String target;

        private save_config() {
        }

        @Override
        protected ConfigWifi doInBackground(JSONObject... net) {
            try {
                SystemWifiConfigGrpc.SystemWifiConfigBlockingStub stub = SystemWifiConfigGrpc.newBlockingStub(channel);
                ConfigWifi request = ConfigWifi.newBuilder().setDns(net[0].getString("dns")).setIp(net[0].getString("ip")).setDhcp(net[0].getBoolean("dhcp")).setGateway(net[0].getString("gateway")).setMask(net[0].getString("mask")).build();
                ConfigWifi reply = stub.sendSystemWifiConfig(request);

//                MqttClientsConfigGrpc.MqttClientsConfigBlockingStub wd = MqttClientsConfigGrpc.newBlockingStub(channel);
//                MqttClients data = MqttClients.newBuilder().build();
//                MqttClients.MqttClient.Builder item =MqttClients.MqttClient.newBuilder().setProtocolValue(4);
//                data.newBuilderForType().addMqttClient(0, item);


                Log.e("APP", "getMessage");

                return reply;
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
        protected void onPostExecute(ConfigWifi result) {
            Log.e("APP", "onPostExecute");
            if (result.getCommandResponce().getIsOK()) {
                Toast.makeText(ctx, "Save successfully", Toast.LENGTH_SHORT).show();
                listener.show_wifi_config(result);
            } else {
                Toast.makeText(ctx, "Interface error", Toast.LENGTH_SHORT).show();

//                listener.failed_wifi_disconnect(result.getResponceData());
            }
//            try {
//                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
    }


    private class get_config extends AsyncTask<wifi, Void, ConfigWifi> {


        String target;

        private get_config() {
        }

        @Override
        protected ConfigWifi doInBackground(wifi... net) {
            try {
                SystemWifiConfigGrpc.SystemWifiConfigBlockingStub stub = SystemWifiConfigGrpc.newBlockingStub(channel);
                ConfigWifi request = ConfigWifi.newBuilder().build();
                ConfigWifi reply = stub.sendSystemWifiConfig(request);

//                MqttClientsConfigGrpc.MqttClientsConfigBlockingStub wd = MqttClientsConfigGrpc.newBlockingStub(channel);
//                MqttClients data = MqttClients.newBuilder().build();
//                MqttClients.MqttClient.Builder item =MqttClients.MqttClient.newBuilder().setProtocolValue(4);
//                data.newBuilderForType().addMqttClient(0, item);


                Log.e("APP", "getMessage");

                return reply;
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
        protected void onPostExecute(ConfigWifi result) {
            Log.e("APP", "onPostExecute");
            if (result.getCommandResponce().getIsOK()) {
                listener.show_wifi_config(result);
            } else {
//                listener.failed_wifi_disconnect(result.getResponceData());
            }
//            try {
//                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
    }


    private class wifistate extends AsyncTask<Boolean, Void, CommandResponce> {


        String target;

        private wifistate() {
        }

        @Override
        protected CommandResponce doInBackground(Boolean... net) {
            try {
                SystemCommandGrpc.SystemCommandBlockingStub stub = SystemCommandGrpc.newBlockingStub(channel);
                Command request;
                if (net[0])
                    request = Command.newBuilder().setCommandType(CommandType.WIFI_STATE).setData("on").build();
                else
                    request = Command.newBuilder().setCommandType(CommandType.WIFI_STATE).setData("off").build();

                CommandResponce reply = stub.sendSystemCommand(request);

//                MqttClientsConfigGrpc.MqttClientsConfigBlockingStub wd = MqttClientsConfigGrpc.newBlockingStub(channel);
//                MqttClients data = MqttClients.newBuilder().build();
//                MqttClients.MqttClient.Builder item =MqttClients.MqttClient.newBuilder().setProtocolValue(4);
//                data.newBuilderForType().addMqttClient(0, item);


                Log.e("APP", "getMessage");

                return reply;
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
        protected void onPostExecute(CommandResponce result) {
            Log.e("APP", "onPostExecute");
            if (result.getIsOK()) {
                if (result.getResponceData().equals("off")) {
                    listener.changestate(false);
                } else
                    listener.changestate(true);
            }
//            try {
//                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
    }


    private class wifidisconnect extends AsyncTask<String, Void, CommandResponce> {


        String target;

        private wifidisconnect() {
        }

        @Override
        protected CommandResponce doInBackground(String... net) {
            try {
                SystemCommandGrpc.SystemCommandBlockingStub stub = SystemCommandGrpc.newBlockingStub(channel);
                Command request = Command.newBuilder().setCommandType(CommandType.WIFI_DISCONNECT).setData(net[0]).build();
                CommandResponce reply = stub.sendSystemCommand(request);

//                MqttClientsConfigGrpc.MqttClientsConfigBlockingStub wd = MqttClientsConfigGrpc.newBlockingStub(channel);
//                MqttClients data = MqttClients.newBuilder().build();
//                MqttClients.MqttClient.Builder item =MqttClients.MqttClient.newBuilder().setProtocolValue(4);
//                data.newBuilderForType().addMqttClient(0, item);


                Log.e("APP", "getMessage");

                return reply;
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
        protected void onPostExecute(CommandResponce result) {
            Log.e("APP", "onPostExecute");
            if (result.getIsOK()) {
                listener.success_wifi_disconnect();
            } else {
                listener.failed_wifi_disconnect(result.getResponceData());
            }
//            try {
//                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
    }


    public class connetwifi extends AsyncTask<String, Void, CommandResponce> {


        String target;
        String pass;
        String name;

        private connetwifi() {
        }

        @Override
        protected CommandResponce doInBackground(String... net) {
            try {
                SystemCommandGrpc.SystemCommandBlockingStub stub = SystemCommandGrpc.newBlockingStub(channel);
                Command request = Command.newBuilder().setCommandType(CommandType.WIFI_CONNECT).setData(net[0]).build();
                CommandResponce reply = stub.sendSystemCommand(request);
                Log.e("APP", "getMessage");
                target = net[0];
                JSONObject obj = new JSONObject(target);
                pass = obj.getString("networkPassword");
                name = obj.getString("networkName");
                return reply;
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                pw.flush();
                return null;
            }
        }

        @Override
        protected void onPostExecute(CommandResponce result) {
            Log.e("APP", "onPostExecute");
            if (result.getIsOK()) {
                listener.success_wifi_connect(target);
                try {
                    JSONObject object = new JSONObject(result.getResponceData());
                    if (wifidb.getwifi(object.getString("networkName")) == null) {
                        WifiSetting wifiSetting = new WifiSetting(0, name, pass, "", "", "", false, false);
                        wifidb.insert(wifiSetting);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                listener.failed_wifi_connect(result.getResponceData());
            }
//            try {
//                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
    }

    public void refresh() {
        getwifilist();
    }

    private class WifiList extends AsyncTask<Void, Void, CommandResponce> {

        private WifiList() {
        }

        @Override
        protected CommandResponce doInBackground(Void... voids) {
            try {
                SystemCommandGrpc.SystemCommandBlockingStub stub = SystemCommandGrpc.newBlockingStub(channel);
                Command request = Command.newBuilder().setCommandID(0).build();
                CommandResponce reply = stub.sendSystemCommand(request);
                Log.e("APP", "getMessage");

                return reply;
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
        protected void onPostExecute(CommandResponce result) {
            Log.e("APP", "onPostExecute");

//                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
            //TODO LISTENER SET WIFI DATA
            if (result != null) {
                ArrayList<wifi> list = new ArrayList<>();
                for (int i = 0; i < result.getWifiListList().size(); i++) {
                    if (result.getWifiListList().get(i).getNetworkName().equals(result.getNemtworkName())){
                        list.add(new wifi(result.getWifiListList().get(i).getNetworkName(), Integer.parseInt(result.getWifiListList().get(i).getNetworksignal().split("/")[0]), true));
                    }else
                        list.add(new wifi(result.getWifiListList().get(i).getNetworkName(), Integer.parseInt(result.getWifiListList().get(i).getNetworksignal().split("/")[0]), false));
                }


                listener.getlist(list);
            } else {
                Toast.makeText(ctx, "Wifi hARDWARE error", Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void saved_network() {
        ctx.startActivity(new Intent(ctx, SavedNetworks.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


}
