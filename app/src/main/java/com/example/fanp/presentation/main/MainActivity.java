package com.example.fanp.presentation.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.fanp.Command;
import com.example.fanp.CommandResponce;
import com.example.fanp.CommandType;
import com.example.fanp.R;
import com.example.fanp.SystemCommandGrpc;
import com.example.fanp.databinding.ActivityMainBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.UserDao;
import com.example.fanp.domain.local.data.WifiDao;
import com.example.fanp.domain.local.repository.User;
import com.example.fanp.domain.local.repository.WifiSetting;
import com.example.fanp.presentation.wifi.WifiActivity;
import com.example.fanp.presentation.wifi.WifiViewModel;
import com.example.fanp.presentation.wifi.wifi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.grpc.ManagedChannel;

public class MainActivity extends DaggerAppCompatActivity {


    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    ManagedChannel channel;

    @Inject
    Context xcs;

    ActivityMainBinding binding;

    // Used to load the 'fanp' library on application startup.
    static {
        System.loadLibrary("fanp");
    }

    @Inject
    UserDao userDao;


    @Inject
    WifiDao wifidb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());


        List<WifiSetting> list = wifidb.all();
        for (WifiSetting item : list) {
            if (item.getAutoConnect()) {
                WifiActivity.modelauto = new wifi(item.getNemtworkName(), 0, true);
                JSONObject data = new JSONObject();
                try {
                    data.put("networkName", item.getNemtworkName());
                    data.put("networkPassword", item.getNetworkPassword());
                    new connetwifi().execute(data.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        viewModel.main = this;
        viewModel.home_frame();
        binding.setViewmodel(viewModel);
        setContentView(binding.getRoot());


    }


    private class connetwifi extends AsyncTask<String, Void, CommandResponce> {


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
//                Toast.makeText(xcs, "failed to connect wifi", Toast.LENGTH_SHORT).show();
            }
//            try {
//                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
    }


    // TODO NATIVE METHOD
    public native String stringFromJNI();
}