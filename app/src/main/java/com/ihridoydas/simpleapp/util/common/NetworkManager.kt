
package com.ihridoydas.simpleapp.util.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings

/**
 * Functions to check network connectivity
 */
sealed class NetworkManagerConnectivity {

    /** Is network online */
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                }
            }
        }
        return false
    }

    /** Is mobile cellular online*/
    fun isCellularOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
               return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            }
        }
        return false
    }

    /** Is  wifi online */
    fun isWifiOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        }
        return false
    }

    /** Is airplane mode on? */
    fun isAirplaneModeOn(context:Context): Boolean
            = Settings.System.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) == 1

}

object NetworkManager  : NetworkManagerConnectivity()