package com.example.fanp.presentation.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.example.fanp.Command;
import com.example.fanp.CommandResponce;
import com.example.fanp.R;
import com.example.fanp.SystemCommandGrpc;
import com.example.fanp.di.module.GrpcModule;
import com.example.fanp.presentation.Financial.FinancialFragment;
import com.example.fanp.presentation.convertprotocol.ConvertProtocol;
import com.example.fanp.presentation.generalsetting.GeneralSettingFragment;
import com.example.fanp.presentation.home.HomeFragment;
import com.example.fanp.presentation.specificsetting.SpecificSettingFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


@SuppressLint("StaticFieldLeak")
public class MainViewModel extends ViewModel {

    private static final String TAG = "APP";

    @Inject
    Context ctx;

    @Inject
    HomeFragment home;

    @Inject
    @Singleton
    SpecificSettingFragment specificSettingFragment;

    @Inject
    @Singleton
    GeneralSettingFragment generalSettingFragment;

    @Inject
    @Singleton
    ConvertProtocol convertProtocol;

    @Inject
    @Singleton
    FinancialFragment financialFragment;


    public MainActivity main; // TODO REMOVE VIEW FROM VIEWMODEL

    @Inject
    public MainViewModel() {
        Log.e(TAG, "AuthViewModel: viewmodel is working...");
    }


    public void refresh() {
        Fragment frag = null;
        final FragmentTransaction transaction = main.getSupportFragmentManager().beginTransaction();
        frag = main.getSupportFragmentManager().findFragmentByTag("Your_Fragment_TAG");
        transaction.detach(frag);
        transaction.attach(frag);
        transaction.commit();
    }

    public void replace_frame(Fragment fr) {
        final FragmentTransaction transaction = main.getSupportFragmentManager().beginTransaction();
        if (fr == specificSettingFragment)
            transaction.replace(R.id.contentContainer, fr, "MYFR");
        else
            transaction.replace(R.id.contentContainer, fr);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void resetcolor() {
        main.binding.imghome.setColorFilter(ctx.getResources().getColor(R.color.black));
        main.binding.imgconvert.setColorFilter(ctx.getResources().getColor(R.color.black));
        main.binding.imgreport.setColorFilter(ctx.getResources().getColor(R.color.black));
        main.binding.imgspecificsetting.setColorFilter(ctx.getResources().getColor(R.color.black));
//        main.binding.imgglobalsetting.setColorFilter(ctx.getResources().getColor(R.color.black));

        main.binding.txthome.setTextColor(ctx.getResources().getColor(R.color.black));
        main.binding.txtconvertprotocol.setTextColor(ctx.getResources().getColor(R.color.black));
        main.binding.txtreport.setTextColor(ctx.getResources().getColor(R.color.black));
        main.binding.txtsecificsetting.setTextColor(ctx.getResources().getColor(R.color.black));
//        main.binding.txtglobalseting.setTextColor(ctx.getResources().getColor(R.color.black));
    }

    public void home_frame() {
        resetcolor();
        main.binding.imghome.setColorFilter(ctx.getResources().getColor(R.color.purple_700));
        main.binding.txthome.setTextColor(ctx.getResources().getColor(R.color.purple_700));
        replace_frame(home);
    }

//    public void general_setting_frame() {
//        resetcolor();
//        main.binding.txtglobalseting.setTextColor(ctx.getResources().getColor(R.color.purple_700));
//        main.binding.imgglobalsetting.setColorFilter(ctx.getResources().getColor(R.color.purple_700));
//        replace_frame(generalSettingFragment);
//    }

    public void specific_setting_frame() {
        resetcolor();
        main.binding.txtsecificsetting.setTextColor(ctx.getResources().getColor(R.color.purple_700));
        main.binding.imgspecificsetting.setColorFilter(ctx.getResources().getColor(R.color.purple_700));
        replace_frame(specificSettingFragment);
    }

    public void convert_protocol_frame() {
        resetcolor();
        main.binding.txtconvertprotocol.setTextColor(ctx.getResources().getColor(R.color.purple_700));
        main.binding.imgconvert.setColorFilter(ctx.getResources().getColor(R.color.purple_700));
        replace_frame(convertProtocol);
    }

    public void financial_frame() {
        resetcolor();
        main.binding.imgreport.setColorFilter(ctx.getResources().getColor(R.color.purple_700));
        main.binding.txtreport.setTextColor(ctx.getResources().getColor(R.color.purple_700));

        replace_frame(financialFragment);
    }


}
