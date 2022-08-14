package com.example.fanp.presentation.mqtt.clientmqtt;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.fanp.ClientActions;
import com.example.fanp.Command;
import com.example.fanp.CommandResponce;
import com.example.fanp.CommandType;
import com.example.fanp.ConfigResponse;
import com.example.fanp.MqttClients;
import com.example.fanp.MqttClientsConfigGrpc;
import com.example.fanp.MqttVarType;
import com.example.fanp.Protocol;
import com.example.fanp.Qos;
import com.example.fanp.SystemCommandGrpc;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.domain.local.repository.WifiSetting;
import com.example.fanp.presentation.mqtt.broker.clientlist.ClientList;
import com.example.fanp.presentation.mqtt.clientmqtt.addclient.AddClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;

import io.grpc.ManagedChannel;

public class MainMqttViewModel extends ViewModel {


    @Inject
    Context ctx;


    @Inject
    I4AllSettingDao db;


    @Inject
    ManagedChannel channel;


    MainMqttClient main;


    @Inject
    public MainMqttViewModel() {
    }


    public void add() {
        ctx.startActivity(new Intent(ctx, AddClient.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    public void exit() {
        main.finish();
    }

    public void updatedata(){
        new updatemqtt().execute();
    }

    public class updatemqtt extends AsyncTask<String, Void, CommandResponce> {

//        public String clientname;
//        public String username;
//        public String protocol;
//        public String reconnect;
//        public String qos;
//        public String willtopic;
//        public String clientid;
//        public String ip;
//        public String port;
//        public String password;
//        public String timeout;
//        public String sessiontime;
//        public boolean sendtimestamp;
//        public boolean keepalive;
//        public boolean compatibleversion;
//        public boolean maintainewill;
//        public boolean willcardsub;


        private updatemqtt() {
        }

        @Override
        protected CommandResponce doInBackground(String... net) {
            try {

                MqttClientsConfigGrpc.MqttClientsConfigBlockingStub stub = MqttClientsConfigGrpc.newBlockingStub(channel);
                MqttClients.Builder config = MqttClients.newBuilder();


                List<I4AllSetting> items = db.getmqttclient();

                for (int i = 0; i < items.size(); i++) {
                    JSONObject object = new JSONObject(items.get(i).getItemsData());
                    MqttClients.MqttClient.Builder client = MqttClients.MqttClient.newBuilder();
                    client.setClientName(object.getString("clientname"));
                    client.setClientID(object.getString("destinationId"));
                    switch (object.getString("protocol")) {
                        case "WS":
                            client.setProtocol(Protocol.WS);
                            break;
                        case "WSS":
                            client.setProtocol(Protocol.WSS);
                            break;
                        case "MQTT/TCP":
                            client.setProtocol(Protocol.MQTTTCP);
                            break;
                        case "MQTT/TLS":
                            client.setProtocol(Protocol.MQTTTTLS);
                            break;
                    }
                    client.setHostAddress(object.getString("ip"));
                    client.setHostPort(object.getInt("port"));
                    client.setUserName(object.getString("username"));
                    client.setUserPassword(object.getString("password"));
                    client.setReConnect(object.getInt("reconnect"));
                    client.setTimeOut(object.getInt("timeout"));
                    client.setWillTopic(object.getString("willtopic"));
                    switch (object.getString("qos")) {
                        case "Almost Once":
                            client.setQos(Qos.ALMOST_ONCE);
                        case "Atleast Once":
                            client.setQos(Qos.ATLEAST_ONCE);
                        case "Exactly Once":
                            client.setQos(Qos.EXACTLY_ONCE);
                        case "ALL":
                            client.setQos(Qos.ALL);
                    }
                    switch (object.getString("qos")) {
                        case "Almost Once":
                            client.setWillQos(Qos.ALMOST_ONCE);
                        case "Atleast Once":
                            client.setWillQos(Qos.ATLEAST_ONCE);
                        case "Exactly Once":
                            client.setWillQos(Qos.EXACTLY_ONCE);
                        case "ALL":
                            client.setWillQos(Qos.ALL);
                    }
                    client.setWillPayLoad("object.getString()");
                    client.setSendTimestamp(object.getBoolean("sendtimestamp"));
                    client.setKeepAlive(object.getBoolean("keepalive"));
                    client.setKeepAliveTime(2000);
                    client.setMqtt31Compatilble(object.getBoolean("compatibleversion"));
                    client.setWillRetain(object.getBoolean("maintainewill"));
                    client.setCleanSession(true);
                    client.setPublishInterval(500);
                    client.setRetain(object.getBoolean("willcardsub"));

                    // set business
                    MqttClients.MqttClient.Business.Builder business = MqttClients.MqttClient.Business.newBuilder();
                    List<I4AllSetting> businesses = db.get_businesses();
                    for (I4AllSetting item : businesses){
                        JSONObject obj = new JSONObject(item.getItemsData());
                        if (obj.getString("id").equals(object.getString("businessid"))){
                            business.setName(obj.getString("name"));
                            business.setSize(obj.getInt("size"));
                            business.setTrpersec(obj.getInt("trpersec"));
                            break;
                        }
                    }
                    client.setBusiness(business);
                    // end business

                    List<I4AllSetting> tags = db.getitembyitesref(object.getInt("clientid"));
                    for (int j = 0; j < tags.size(); j++) {
                        JSONObject object1 = new JSONObject(tags.get(j).getItemsData());
                        MqttClients.MqttClient.ClientTag.Builder tag = MqttClients.MqttClient.ClientTag.newBuilder();
                        tag.setTagName(object1.getString("tagname"));
                        tag.setTopicName(object1.getString("topicname"));
                        switch (object1.getString("type")) {
                            case "PLAIN":
                                tag.setMqttVarType(MqttVarType.PLAIN);
                                break;
                            case "JSON":
                                tag.setMqttVarType(MqttVarType.JSON);
                                break;
                            case "CSV":
                                tag.setMqttVarType(MqttVarType.CSV);
                                break;
                        }
                        switch (object1.getString("subpub")) {
                            case "PUB_SUB": tag.setClientActions(ClientActions.PUB_SUB);break;
                            case "Sub": tag.setClientActions(ClientActions.SUB);break;
                            case "Pub": tag.setClientActions(ClientActions.PUB);break;
                        }

                        tag.setOnOff(false);
                        tag.setSystemName("Mohammad");

                        client.addClientTag(tag);
                    }
                    config.addMqttClient(client);
                }

                ConfigResponse replay = stub.sendMqttClientsConfig(config.build());


                return null;
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

//            try {
//                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
    }


}
