package com.example.fanp.di.module;


import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.presentation.modbus.GrpcModBus;
import com.example.fanp.presentation.s7.S7Grpc;

import dagger.Module;
import dagger.Provides;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Module
public class GrpcModule {

    String host =  "192.168.0.251";
    int portStr = 50051;


    @Provides
    public ManagedChannel providerManagedChannel(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, portStr).usePlaintext().build();
        return channel;
    }


    @Provides
    public GrpcModBus providgrpcmodbus(ManagedChannel channel, I4AllSettingDao db){
        GrpcModBus grpcModBus = new GrpcModBus(channel,db);
        return grpcModBus;
    }


    @Provides
    public S7Grpc providS7Grpc(ManagedChannel channel, I4AllSettingDao db){
        S7Grpc s7Grpc = new S7Grpc(channel,db);
        return s7Grpc;
    }


}
