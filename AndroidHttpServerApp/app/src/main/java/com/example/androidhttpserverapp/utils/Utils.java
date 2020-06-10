package com.example.androidhttpserverapp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Utils
 */
public class Utils {

    /**
     * getIpAccess
     * @return Ip Address
     */
    @SuppressLint("DefaultLocale")
    public static String getIpAccess(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        return String.format(
                Constants.IP_ADDRESS_FORMAT,
                (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));
    }
}
