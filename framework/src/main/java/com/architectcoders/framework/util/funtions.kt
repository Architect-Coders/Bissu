package com.architectcoders.framework.util

import android.content.Context
import android.net.ConnectivityManager

fun Context.isAvailableNetwork(): Boolean {
    val connectivityManager =this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}