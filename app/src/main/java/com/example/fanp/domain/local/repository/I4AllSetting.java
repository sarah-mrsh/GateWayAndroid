package com.example.fanp.domain.local.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "i4AllSetting")
public class I4AllSetting {




    @PrimaryKey(autoGenerate = true)
    int allSettingId;

    @ColumnInfo(name = "itemsRef")
    int itemsRef;

    @ColumnInfo(name = "itemsData")
    String itemsData;

    @ColumnInfo(name = "isDeleted")
    boolean isDeleted;

    @ColumnInfo(name = "dateTime")
    String dateTime;


    public I4AllSetting(int allSettingId, int itemsRef, String itemsData, boolean isDeleted, String dateTime) {
        this.allSettingId = allSettingId;
        this.itemsRef = itemsRef;
        this.itemsData = itemsData;
        this.isDeleted = isDeleted;
        this.dateTime = dateTime;
    }

    public int getAllSettingId() {
        return allSettingId;
    }

    public void setAllSettingId(int allSettingId) {
        this.allSettingId = allSettingId;
    }

    public int getItemsRef() {
        return itemsRef;
    }

    public void setItemsRef(int itemsRef) {
        this.itemsRef = itemsRef;
    }

    public String getItemsData() {
        return itemsData;
    }

    public void setItemsData(String itemsData) {
        this.itemsData = itemsData;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
