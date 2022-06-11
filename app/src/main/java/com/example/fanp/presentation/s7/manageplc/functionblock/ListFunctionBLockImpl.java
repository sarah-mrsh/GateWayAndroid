package com.example.fanp.presentation.s7.manageplc.functionblock;

import com.example.fanp.domain.local.repository.I4AllSetting;

public interface ListFunctionBLockImpl {
    void delete(I4AllSetting item);
    void edit(I4AllSetting item);
    void addparameter(I4AllSetting item);
}
