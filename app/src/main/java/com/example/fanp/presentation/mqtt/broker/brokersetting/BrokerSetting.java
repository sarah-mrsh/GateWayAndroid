package com.example.fanp.presentation.mqtt.broker.brokersetting;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


import com.example.fanp.ClientActions;
import com.example.fanp.ConfigResponse;

import com.example.fanp.MqttBorker;
import com.example.fanp.MqttBrokerConfigGrpc;
import com.example.fanp.MqttVarType;
import com.example.fanp.Protocol;
import com.example.fanp.Qos;
import com.example.fanp.R;

import com.example.fanp.databinding.ActivityBrokerSettingBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.grpc.ManagedChannel;

public class BrokerSetting extends DaggerAppCompatActivity {


    ActivityBrokerSettingBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    public BrokerSettingViewmodel viewmodel;

    @Inject
    Context ctx;

    @Inject
    I4AllSettingDao db;

    @Inject
    ManagedChannel channel;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new savebrokermqtt().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_broker_setting);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(BrokerSettingViewmodel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;


        I4AllSetting ddbdata = db.getbrokersetting();
        if (ddbdata != null)
            try {
                JSONObject data = new JSONObject(ddbdata.getItemsData());
                if (data.has("brokername")) {
                    viewmodel.brokername = (data.getString("brokername"));
                }
                if (data.has("username")) {
                    viewmodel.username = (data.getString("username"));
                }
                if (data.has("protocol")) {
                    viewmodel.protocol = (data.getString("protocol"));
                }
                if (data.has("maxclient")) {
                    viewmodel.maxclient = (data.getString("maxclient"));
                }
                if (data.has("qos")) {
                    viewmodel.qos = (data.getString("qos"));
                }
                if (data.has("retainmessage")) {
                    viewmodel.retainmessage = (data.getString("retainmessage"));
                }
                if (data.has("brokerid")) {
                    viewmodel.brokerid = (data.getString("brokerid"));
                }
                if (data.has("ip")) {
                    viewmodel.ip = (data.getString("ip"));
                }
                if (data.has("port")) {
                    viewmodel.port = (data.getString("port"));
                }
                if (data.has("password")) {
                    viewmodel.password = (data.getString("password"));
                }
                if (data.has("maxlenght")) {
                    viewmodel.maxlenght = (data.getString("maxlenght"));
                }
                if (data.has("maxque")) {
                    viewmodel.maxque = (data.getString("maxque"));
                }
                if (data.has("sessiontime")) {
                    viewmodel.sessiontime = (data.getString("sessiontime"));
                }
                if (data.has("sendtimestamp")) {
                    viewmodel.sendtimestamp = (data.getBoolean("sendtimestamp"));
                }
                if (data.has("keepalive")) {
                    viewmodel.keepalive = (data.getBoolean("keepalive"));
                }
                if (data.has("compatibleversion")) {
                    viewmodel.compatibleversion = (data.getBoolean("compatibleversion"));
                }
                if (data.has("maintainewill")) {
                    viewmodel.maintainewill = (data.getBoolean("maintainewill"));
                }
                if (data.has("willcardsub")) {
                    viewmodel.willcardsub = (data.getBoolean("willcardsub"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        setContentView(binding.getRoot());
    }

    private class savebrokermqtt extends AsyncTask<Void, Void, ConfigResponse> {

        private savebrokermqtt() {
        }

        @Override
        protected ConfigResponse doInBackground(Void... voids) {
            try {
                MqttBrokerConfigGrpc.MqttBrokerConfigBlockingStub stub = MqttBrokerConfigGrpc.newBlockingStub(channel);
                MqttBorker.Builder data = MqttBorker.newBuilder();
                data.setClientName(viewmodel.brokername);
                data.setClientID(viewmodel.brokerid);
                switch (viewmodel.protocol) {
                    case "WS":
                        data.setProtocol(Protocol.WS);
                        break;
                    case "WSS":
                        data.setProtocol(Protocol.WSS);
                        break;
                    case "MQTTTCP":
                        data.setProtocol(Protocol.MQTTTCP);
                        break;
                    case "MQTTTTLS":
                        data.setProtocol(Protocol.MQTTTTLS);
                        break;
                }
                data.setHostAddress(viewmodel.ip);
                data.setHostPort(Integer.parseInt(viewmodel.port));
                data.setMaxCient(Integer.parseInt(viewmodel.maxclient));
                data.setMaxLenght(Integer.parseInt(viewmodel.maxlenght));
                switch (viewmodel.qos) {
                    case "ALMOST_ONCE":
                        data.setBrokerQos(Qos.ALMOST_ONCE);
                        break;
                    case "ATLEAST_ONCE":
                        data.setBrokerQos(Qos.ATLEAST_ONCE);
                        break;
                    case "EXACTLY_ONCE":
                        data.setBrokerQos(Qos.EXACTLY_ONCE);
                        break;
                }
                data.setMaxQueLeght(Integer.parseInt(viewmodel.maxque));
                data.setRetainMessage(Integer.parseInt(viewmodel.retainmessage));
                data.setKeepAlive(viewmodel.keepalive);
                data.setMqtt31Compatilble(viewmodel.compatibleversion);
                data.setRetainWill(viewmodel.maintainewill);
                data.setWildcardSub(viewmodel.willcardsub);
                // add tags
                List<I4AllSetting> dbtags = db.gettags();
                for (I4AllSetting item : dbtags) {
                    MqttBorker.BrokerTag.Builder tag = MqttBorker.BrokerTag.newBuilder();
                    JSONObject object = new JSONObject(item.getItemsData());
                    tag.setTagName(object.getString("tagname"));
                    switch (object.getString("tagtype")) {
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
//                    tag.setTimer(Int32Value.parseFrom(ByteString.copyFromUtf8(object.getString("tagschedule"))));
                    tag.setTimer(Integer.parseInt(object.getString("tagschedule")));
                    tag.setSystemName(object.getString("systemname"));
                    tag.setTopicName(object.getString("topicname"));
//                    object.getString("des");
                    data.addBrokerTag(tag);
                }
                List<I4AllSetting> dbclient = db.getclients();
                for (I4AllSetting item : dbclient) {
                    MqttBorker.BrokerClient.Builder client = MqttBorker.BrokerClient.newBuilder();
                    JSONObject object = new JSONObject(item.getItemsData());
                    client.setBrokerClientID(object.getString("clientid"));
                    client.setBrokerClientName(object.getString("clientname"));
                    switch (object.getString("qos")) {
                        case "ALMOST_ONCE":
                            client.setQos(Qos.ALMOST_ONCE);
                            break;
                        case "ATLEAST_ONCE":
                            client.setQos(Qos.ATLEAST_ONCE);
                            break;
                        case "EXACTLY_ONCE":
                            client.setQos(Qos.EXACTLY_ONCE);
                            break;
                    }
                    switch (object.getString("qos")) {
                        case "PUB":
                            client.setClientActions(ClientActions.PUB);
                            break;
                        case "SUB":
                            client.setClientActions(ClientActions.SUB);
                            break;
                        case "PUB_SUB":
                            client.setClientActions(ClientActions.PUB_SUB);
                            break;
                    }
                    // insert topics
                    I4AllSetting dbtopics = db.gettopics(item.getAllSettingId());
                    if (dbtopics != null) {


                        JSONArray array = new JSONArray(dbtopics.getItemsData());
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject topic = array.getJSONObject(i);
                            MqttBorker.BrokerClient.ClientTopic.Builder top = MqttBorker.BrokerClient.ClientTopic.newBuilder();
                            top.setTopicName(topic.getString("name"));
                            top.setTopicName(topic.getString("clientname"));
                            switch (topic.getString("qos")) {
                                case "ALMOST_ONCE":
                                    top.setQos(Qos.ALMOST_ONCE);
                                    break;
                                case "ATLEAST_ONCE":
                                    top.setQos(Qos.ATLEAST_ONCE);
                                    break;
                                case "EXACTLY_ONCE":
                                    top.setQos(Qos.EXACTLY_ONCE);
                                    break;
                            }
                            top.setPrivate(topic.getBoolean("privated"));
                            top.setRetain(topic.getBoolean("retain"));
                            client.addClientTopic(top);
                        }
                        data.addBrokerClient(client);
                    }
                }
                ConfigResponse replay = stub.sendMqttBrokerConfig(data.build());


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
        protected void onPostExecute(ConfigResponse result) {
            Log.e("APP", "onPostExecute");
//            try {
//                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
            //TODO LISTENER SET WIFI DATA
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
    }

}