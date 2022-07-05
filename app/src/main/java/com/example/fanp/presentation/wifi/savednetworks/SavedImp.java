package com.example.fanp.presentation.wifi.savednetworks;

import com.example.fanp.domain.local.repository.WifiSetting;
import com.example.fanp.presentation.wifi.wifi;

public interface SavedImp {
    void forget(WifiSetting item);
}
