package com.example.fanp.presentation.s7.manageplc.functionblock.parameters;

import com.example.fanp.domain.local.repository.I4AllSetting;

public interface ListParameterImpl {
    void delete(I4AllSetting item);

    void edit(I4AllSetting item);
}
