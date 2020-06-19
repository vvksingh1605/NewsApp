package net.test.newsappvivek

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager


object utils{
    @SuppressLint("WrongConstant")
    fun isNetworkAvailable(context: Context?): Boolean {
        return if (context == null) {
            false
        } else {
            try {
                val ConnectMgr =
                    context.getSystemService("connectivity") as ConnectivityManager
                if (ConnectMgr == null) {
                    false
                } else {
                    val NetInfo = ConnectMgr.activeNetworkInfo
                    NetInfo?.isConnected ?: false
                }
            } catch (var3: SecurityException) {
                var3.printStackTrace()
                false
            }
        }
    }
}