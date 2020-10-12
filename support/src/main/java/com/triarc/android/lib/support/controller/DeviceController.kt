package com.triarc.android.lib.support.controller

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import java.net.NetworkInterface
import java.util.*

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
object DeviceController {

    private const val NO_SIGNAL = -127
    private const val MAX_LEVEL = 5
    private const val DEFAULT_DEVICE_ID = "00:00:00:00:00:02"

    fun getWifiStrength(context: Context): Int {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager
        wifiManager?.scanResults
        val wifiInfo = wifiManager?.connectionInfo
        return WifiManager.calculateSignalLevel(wifiInfo?.rssi ?: NO_SIGNAL, MAX_LEVEL)
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    fun getDeviceHardwareId(context: Context): String {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            try {
                val all = Collections.list(NetworkInterface.getNetworkInterfaces())
                for (nif in all) {
                    if (!nif.name.equals("wlan0", ignoreCase = true))
                        continue

                    val macBytes = nif.hardwareAddress ?: return ""

                    val res1 = StringBuilder()
                    for (b in macBytes) {
                        res1.append(String.format("%02X:", b))
                    }

                    if (res1.isNotEmpty()) {
                        res1.deleteCharAt(res1.length - 1)
                    }
                    return res1.toString().toUpperCase(Locale.getDefault())
                }
            } catch (ex: Exception) {
                return DEFAULT_DEVICE_ID
            }
        } else {
            return try {
                val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wInfo = wifiManager.connectionInfo

                wInfo.macAddress.toUpperCase(Locale.getDefault())
            } catch (ex: Exception) {
                DEFAULT_DEVICE_ID
            }
        }
        return DEFAULT_DEVICE_ID
    }
}
