package com.example.fanp.domain.local.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fanp.domain.local.repository.I4AllSetting;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface I4AllSettingDao {

    @Query("SELECT * FROM i4AllSetting")
    List<I4AllSetting> all();

    @Query("SELECT COUNT(*) from i4AllSetting")
    int count();

    @Insert
    void insert(I4AllSetting... i4AllSettings);


    @Delete
    void delete(I4AllSetting... i4AllSettings);


    @Update
    void update(I4AllSetting... i4AllSettings);

    @Query("SELECT * FROM I4AllSetting WHERE itemsRef =:orderId")
    I4AllSetting getitembyId(int orderId);


    @Query("SELECT * FROM i4AllSetting where itemsRef=501")
    List<I4AllSetting> gettags();

    @Query("SELECT * FROM i4AllSetting where itemsRef=502")
    List<I4AllSetting> getclients();

    @Query("SELECT * FROM i4AllSetting where itemsRef=500")
    I4AllSetting getbrokersetting();
    @Query("SELECT * FROM i4AllSetting where itemsRef=517")
    List<I4AllSetting> getmqttclient();

    @Query("SELECT * FROM i4AllSetting where itemsRef=509")
    I4AllSetting gettcpmodbus();

    @Query("SELECT * FROM i4AllSetting where itemsRef=512")
    I4AllSetting getrtumodbus();

    @Query("SELECT * FROM i4AllSetting where itemsRef=513")
    I4AllSetting getrtumodbusserverlist();

    @Query("SELECT * FROM i4AllSetting where itemsRef=519")
    List<I4AllSetting> getplc();



    @Query("SELECT * FROM i4AllSetting where allSettingId=:id")
    I4AllSetting finditem(int id);


    @Query("SELECT * FROM i4AllSetting where itemsRef=:id")
    I4AllSetting gettopics(int id);


    @Query("SELECT * FROM i4AllSetting where itemsRef=:id")
    List<I4AllSetting> getitembyitesref(int id);


    @Query("SELECT * FROM i4AllSetting where itemsRef=640")
    List<I4AllSetting> getconvertprotocols();

}
