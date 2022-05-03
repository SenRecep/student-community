package com.example.student_community.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class LiveNetworkListener {
    companion object {
        private fun getConnectionType(context: Context): Int {
            var result = 0;
            var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
            cm.run {
                this?.getNetworkCapabilities(this.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) result = 1;
                    if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) result = 2;
                }
            }
            return result;
        }

        fun isConnected(): Boolean {
            var context = GlobalApp.getAppContext();
            var result = getConnectionType(context);
            return result != 0;
        }
    }
}