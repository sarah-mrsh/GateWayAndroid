package com.example.fanp.di.module;

import androidx.lifecycle.ViewModel;

import com.example.fanp.di.key.ViewModelKey;
import com.example.fanp.presentation.account.AccountUserViewmodel;
import com.example.fanp.presentation.business.MainBusinessViewModel;
import com.example.fanp.presentation.business.add.AddBusinessViewModel;
import com.example.fanp.presentation.lan.LanViewModel;
import com.example.fanp.presentation.main.MainViewModel;
import com.example.fanp.presentation.modbus.rtu.MainModbudRtuViewModel;
import com.example.fanp.presentation.modbus.rtu.addserver.AddServerViewModel;
import com.example.fanp.presentation.modbus.rtu.serverlistrtu.ServerListRTUViewModel;
import com.example.fanp.presentation.modbus.rtu.tag.AddTagViewModel;
import com.example.fanp.presentation.modbus.rtu.taglist.TagListViewModel;
import com.example.fanp.presentation.modbus.tcp.MainModeBusTCPViewModel;
import com.example.fanp.presentation.modbus.tcp.addserver.AddServeTcpViewModel;
import com.example.fanp.presentation.modbus.tcp.serverlist.ServerLisViewMod;
import com.example.fanp.presentation.modbus.tcp.tag.AddTagTCPViewModel;
import com.example.fanp.presentation.modbus.tcp.taglisttcp.TagListTcpViewModel;
import com.example.fanp.presentation.mport.MportViewModel;
import com.example.fanp.presentation.mqtt.broker.brokersetting.BrokerSettingViewmodel;
import com.example.fanp.presentation.mqtt.broker.clientlist.ClientListViewModel;
import com.example.fanp.presentation.mqtt.broker.taglist.LocalBrokerTagViewModel;
import com.example.fanp.presentation.mqtt.broker.topic.AddTopicViewModel;
import com.example.fanp.presentation.mqtt.clientmqtt.MainMqttViewModel;
import com.example.fanp.presentation.mqtt.clientmqtt.addclient.AddClientViewModel;
import com.example.fanp.presentation.mqtt.clientmqtt.addtag.AddTagMqttViewModel;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.TagListMqttViweModel;
import com.example.fanp.presentation.node.NodeListViewModel;
import com.example.fanp.presentation.node.add.AddNodeViewModel;
import com.example.fanp.presentation.porthistory.PortHistoryViewModel;
import com.example.fanp.presentation.protocolmanagment.MprotocolManagmentViewModel;
import com.example.fanp.presentation.s7.MainS7ViewModel;
import com.example.fanp.presentation.s7.addplc.AddPlcViewModel;
import com.example.fanp.presentation.s7.manageplc.ManagePLCViewModel;
import com.example.fanp.presentation.s7.manageplc.adddatablockplc.AddDataBlockPlcViewModel;
import com.example.fanp.presentation.s7.manageplc.datablockplc.DatablockplcViewModel;
import com.example.fanp.presentation.s7.manageplc.functionblock.addfunctionblock.AddFunctionBlockPlcViewModel;
import com.example.fanp.presentation.s7.manageplc.functionblock.parameters.MainParameterViewModel;
import com.example.fanp.presentation.s7.manageplc.functionblock.parameters.addparameter.AddParameterViewModel;
import com.example.fanp.presentation.s7.plclist.PLCListViewModel;
import com.example.fanp.presentation.s7.tag.MainTagViewModel;
import com.example.fanp.presentation.s7.tag.datablock.AddTagDataBlockViewModel;
import com.example.fanp.presentation.s7.tag.datablock.TagListDataBlockViewModel;
import com.example.fanp.presentation.s7.tag.io.AddTagIOViewmodel;
import com.example.fanp.presentation.s7.tag.io.TagListIOViewModel;
import com.example.fanp.presentation.s7.tag.memory.AddTagMemoryViewModel;
import com.example.fanp.presentation.s7.tag.memory.TagListMemoryVIewModel;
import com.example.fanp.presentation.serialactivity.SerialViewModel;
import com.example.fanp.presentation.wifi.WifiViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindsAuthViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AccountUserViewmodel.class)
    public abstract ViewModel bindsViewModel(AccountUserViewmodel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(LanViewModel.class)
    public abstract ViewModel bindsLanViewModel(LanViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MportViewModel.class)
    public abstract ViewModel bindsMportViewModel(MportViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(PortHistoryViewModel.class)
    public abstract ViewModel bindsPortHistoryViewModel(PortHistoryViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(MprotocolManagmentViewModel.class)
    public abstract ViewModel bindsMprotocolManagmentViewModel(MprotocolManagmentViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(SerialViewModel.class)
    public abstract ViewModel bindsSerialViewModel(SerialViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(BrokerSettingViewmodel.class)
    public abstract ViewModel bindsBrokerSettingViewmodel(BrokerSettingViewmodel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LocalBrokerTagViewModel.class)
    public abstract ViewModel bindsLocalBrokerTagViewModel(LocalBrokerTagViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(WifiViewModel.class)
    public abstract ViewModel bindsWifiViewModel(WifiViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ClientListViewModel.class)
    public abstract ViewModel bindsWClientListViewModel(ClientListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddTopicViewModel.class)
    public abstract ViewModel bindsaddtopicviewmodel(AddTopicViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainModeBusTCPViewModel.class)
    public abstract ViewModel bindsMainModeBusTCPViewModel(MainModeBusTCPViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ServerLisViewMod.class)
    public abstract ViewModel bindsServerLisViewMod(ServerLisViewMod viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddServeTcpViewModel.class)
    public abstract ViewModel bindsAddServeTcpViewModel(AddServeTcpViewModel viewModel);



    @Binds
    @IntoMap
    @ViewModelKey(MainModbudRtuViewModel.class)
    public abstract ViewModel bindsMainModbudRtuViewModel(MainModbudRtuViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ServerListRTUViewModel.class)
    public abstract ViewModel bindsServerListRTUViewModel(ServerListRTUViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(AddServerViewModel.class)
    public abstract ViewModel bindsAddServerViewModel(AddServerViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(TagListViewModel.class)
    public abstract ViewModel bindsTagListViewModel(TagListViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(AddTagViewModel.class)
    public abstract ViewModel bindsAAddTagViewModel(AddTagViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(TagListTcpViewModel.class)
    public abstract ViewModel bindsTagListTcpViewModel(TagListTcpViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddTagTCPViewModel.class)
    public abstract ViewModel bindsAddTagTCPViewModel(AddTagTCPViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainS7ViewModel.class)
    public abstract ViewModel bindsMainS7ViewModel(MainS7ViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ManagePLCViewModel.class)
    public abstract ViewModel bindsManagePLCViewModel(ManagePLCViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PLCListViewModel.class)
    public abstract ViewModel bindsPLCListViewModel(PLCListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddPlcViewModel.class)
    public abstract ViewModel bindsAddPlcViewModel(AddPlcViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(MainTagViewModel.class)
    public abstract ViewModel bindsMainTagViewModel(MainTagViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TagListDataBlockViewModel.class)
    public abstract ViewModel bindsTagListDataBlockViewModel(TagListDataBlockViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddTagDataBlockViewModel.class)
    public abstract ViewModel bindsAddTagDataBlockViewModel(AddTagDataBlockViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddTagIOViewmodel.class)
    public abstract ViewModel bindsAddTagIOViewmodel(AddTagIOViewmodel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TagListIOViewModel.class)
    public abstract ViewModel bindsTagListIOViewModel(TagListIOViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DatablockplcViewModel.class)
    public abstract ViewModel bindsDatablockplcViewModel(DatablockplcViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddDataBlockPlcViewModel.class)
    public abstract ViewModel bindsAddDataBlockPlcViewModel(AddDataBlockPlcViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(AddClientViewModel.class)
    public abstract ViewModel bindsAddClientViewModel(AddClientViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(AddTagMqttViewModel.class)
    public abstract ViewModel bindsAddTagMqttViewModel(AddTagMqttViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(TagListMqttViweModel.class)
    public abstract ViewModel bindsTagListViweModel(TagListMqttViweModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainMqttViewModel.class)
    public abstract ViewModel bindsMainMqttViewModel(MainMqttViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(AddFunctionBlockPlcViewModel.class)
    public abstract ViewModel bindsAddFunctionBlockPlcViewModel(AddFunctionBlockPlcViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddParameterViewModel.class)
    public abstract ViewModel bindsAddParameterViewModel(AddParameterViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(MainParameterViewModel.class)
    public abstract ViewModel bindsMainParameterViewModel(MainParameterViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(TagListMemoryVIewModel.class)
    public abstract ViewModel bindsTagListMemoryVIewModel(TagListMemoryVIewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddTagMemoryViewModel.class)
    public abstract ViewModel bindsAddTagMemoryViewModel(AddTagMemoryViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(NodeListViewModel.class)
    public abstract ViewModel bindsNodeListViewModel(NodeListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddNodeViewModel.class)
    public abstract ViewModel bindsAddNodeViewModel(AddNodeViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(MainBusinessViewModel.class)
    public abstract ViewModel bindsMainBusinessViewModel(MainBusinessViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddBusinessViewModel.class)
    public abstract ViewModel bindsAddBusinessViewModel(AddBusinessViewModel viewModel);

}
