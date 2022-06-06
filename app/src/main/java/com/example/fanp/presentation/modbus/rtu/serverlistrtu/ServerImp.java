package com.example.fanp.presentation.modbus.rtu.serverlistrtu;


import com.example.fanp.domain.local.repository.I4AllSetting;

public interface ServerImp {
    void edit(I4AllSetting item, int index);
    void delete(I4AllSetting item,int index);
    void taglist(I4AllSetting item,int index);

}
