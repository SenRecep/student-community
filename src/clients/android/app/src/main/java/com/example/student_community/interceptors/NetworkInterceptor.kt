package com.example.student_community.interceptors

import com.example.student_community.exceptions.OfflineException
import com.example.student_community.utility.LiveNetworkListener
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!LiveNetworkListener.isConnected()) throw OfflineException("");
        var req= chain.request();
        return chain.proceed(req);
    }
}