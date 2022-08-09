package com.example.fanp.di.module;

import com.example.fanp.di.scope.ActivityScope;
import com.example.fanp.presentation.account.AccountUser;
import com.example.fanp.presentation.ble.BleActivity;
import com.example.fanp.presentation.lan.LanActivity;
import com.example.fanp.presentation.main.MainActivity;
import com.example.fanp.presentation.modbus.rtu.MainModBusRTU;
import com.example.fanp.presentation.modbus.rtu.addserver.AddServerRtu;
import com.example.fanp.presentation.modbus.rtu.serverlistrtu.ServerListRtu;
import com.example.fanp.presentation.modbus.rtu.tag.AddTag;
import com.example.fanp.presentation.modbus.rtu.taglist.TagListRtu;
import com.example.fanp.presentation.modbus.tcp.MainModbusTCP;
import com.example.fanp.presentation.modbus.tcp.addserver.AddServertcp;
import com.example.fanp.presentation.modbus.tcp.serverlist.ServerList;
import com.example.fanp.presentation.modbus.tcp.tag.AddTagTCP;
import com.example.fanp.presentation.modbus.tcp.taglisttcp.TaglistTcp;
import com.example.fanp.presentation.mport.MportActivity;
import com.example.fanp.presentation.mqtt.broker.brokersetting.BrokerSetting;
import com.example.fanp.presentation.mqtt.broker.clientlist.ClientList;
import com.example.fanp.presentation.mqtt.broker.taglist.Localbrokertaglist;
import com.example.fanp.presentation.mqtt.broker.topic.Addtopic;
import com.example.fanp.presentation.mqtt.clientmqtt.MainMqttClient;
import com.example.fanp.presentation.mqtt.clientmqtt.addclient.AddClient;
import com.example.fanp.presentation.mqtt.clientmqtt.addtag.AddTagMqttClient;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.TagListMqttClient;
import com.example.fanp.presentation.node.NodeList;
import com.example.fanp.presentation.node.add.AddNode;
import com.example.fanp.presentation.protocolmanagment.MProtocolManagment;
import com.example.fanp.presentation.s7.MainS7;
import com.example.fanp.presentation.s7.addplc.AddPlc;
import com.example.fanp.presentation.s7.manageplc.ManagePLC;
import com.example.fanp.presentation.s7.manageplc.adddatablockplc.AddDataBlockPlc;
import com.example.fanp.presentation.s7.manageplc.datablockplc.DataBlockPlc;
import com.example.fanp.presentation.s7.manageplc.functionblock.FunctionBlockMainPlc;
import com.example.fanp.presentation.s7.manageplc.functionblock.addfunctionblock.AddFunctionBlockPlc;
import com.example.fanp.presentation.s7.manageplc.functionblock.parameters.MainParameter;
import com.example.fanp.presentation.s7.manageplc.functionblock.parameters.addparameter.AddParameterFunctionBlock;
import com.example.fanp.presentation.s7.plclist.PLCList;
import com.example.fanp.presentation.s7.tag.MainTag;
import com.example.fanp.presentation.s7.tag.datablock.AddDatatBlockTag;
import com.example.fanp.presentation.s7.tag.datablock.TagListDatablock;
import com.example.fanp.presentation.s7.tag.io.AddTagIo;
import com.example.fanp.presentation.s7.tag.io.TagListIo;
import com.example.fanp.presentation.s7.tag.memory.AddTagMemory;
import com.example.fanp.presentation.s7.tag.memory.TaglistMemory;
import com.example.fanp.presentation.serialactivity.SerialActivity;
import com.example.fanp.presentation.wifi.WifiActivity;
import com.example.fanp.presentation.wifi.savednetworks.SavedNetworks;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract PLCList plcList();


    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AccountUser accountUser();


    @ActivityScope
    @ContributesAndroidInjector
    abstract BleActivity bleActivity();



    @ActivityScope
    @ContributesAndroidInjector
    abstract LanActivity lanActivity();


    @ActivityScope
    @ContributesAndroidInjector
    abstract MportActivity mportActivity();


    @ActivityScope
    @ContributesAndroidInjector
    abstract MProtocolManagment mProtocolManagment();


    @ActivityScope
    @ContributesAndroidInjector
    abstract SerialActivity serialActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract BrokerSetting Brokersettingprovider();


    @ActivityScope
    @ContributesAndroidInjector
    abstract Localbrokertaglist localbrokertaglist();

    @ActivityScope
    @ContributesAndroidInjector
    abstract WifiActivity wifiActivity();


    @ActivityScope
    @ContributesAndroidInjector
    abstract ClientList clientList();

    @ActivityScope
    @ContributesAndroidInjector
    abstract Addtopic addtopic();


    @ActivityScope
    @ContributesAndroidInjector
    abstract MainModbusTCP mainModbusTCP();


    @ActivityScope
    @ContributesAndroidInjector
    abstract ServerList serverList();


    @ActivityScope
    @ContributesAndroidInjector
    abstract AddServertcp addServertcp();


    @ActivityScope
    @ContributesAndroidInjector
    abstract MainModBusRTU mainModBusRTU();


    @ActivityScope
    @ContributesAndroidInjector
    abstract ServerListRtu serverListRtu();


    @ActivityScope
    @ContributesAndroidInjector
    abstract AddServerRtu addServerRtu();


    @ActivityScope
    @ContributesAndroidInjector
    abstract TagListRtu tagListRtu();


    @ActivityScope
    @ContributesAndroidInjector
    abstract AddTag addTag();


    @ActivityScope
    @ContributesAndroidInjector
    abstract TaglistTcp taglistTcp();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddTagTCP addTagTCP();

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainS7 mainS7();

    @ActivityScope
    @ContributesAndroidInjector
    abstract ManagePLC managePLC();


    @ActivityScope
    @ContributesAndroidInjector
    abstract AddDatatBlockTag addDatatBlockTag();


    @ActivityScope
    @ContributesAndroidInjector
    abstract TagListDatablock tagListDatablock();


    @ActivityScope
    @ContributesAndroidInjector
    abstract AddTagIo addTagIo();


    @ActivityScope
    @ContributesAndroidInjector
    abstract TagListIo tagListIo();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddTagMemory addTagMemory();

    @ActivityScope
    @ContributesAndroidInjector
    abstract TaglistMemory taglistMemory();


    @ActivityScope
    @ContributesAndroidInjector
    abstract MainTag mainTag();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddPlc addPlc();

    @ActivityScope
    @ContributesAndroidInjector
    abstract SavedNetworks savedNetworks();

    @ActivityScope
    @ContributesAndroidInjector
    abstract DataBlockPlc dataBlockPlc();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddClient addClient();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddTagMqttClient addTagMqttClient();


    @ActivityScope
    @ContributesAndroidInjector
    abstract TagListMqttClient tagListMqttClient();

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainMqttClient mainMqttClient();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddDataBlockPlc addDataBlockPlc();

    @ActivityScope
    @ContributesAndroidInjector
    abstract FunctionBlockMainPlc functionBlockMainPlc();


    @ActivityScope
    @ContributesAndroidInjector
    abstract AddFunctionBlockPlc addFunctionBlockPlc();


    @ActivityScope
    @ContributesAndroidInjector
    abstract AddParameterFunctionBlock addParameterFunctionBlock();

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainParameter mainParameter();

    @ActivityScope
    @ContributesAndroidInjector
    abstract NodeList nodeList();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddNode addNode();


}
