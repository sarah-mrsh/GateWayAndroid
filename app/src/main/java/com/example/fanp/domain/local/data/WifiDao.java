package com.example.fanp.domain.local.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.domain.local.repository.User;
import com.example.fanp.domain.local.repository.WifiSetting;

import java.util.List;

@Dao
public interface WifiDao {

    @Query("SELECT * FROM wifitbl")
    List<WifiSetting> all();

    @Query("SELECT COUNT(*) from wifitbl")
    int count();

    @Query("SELECT * FROM wifitbl WHERE nemtworkName =:name")
    WifiSetting getwifi(String  name);


    @Insert
    void insert(WifiSetting... wifitbl);

    @Update
    void update(WifiSetting... wifitbl);

    @Delete
    void delete(WifiSetting... wifitbl);

}
