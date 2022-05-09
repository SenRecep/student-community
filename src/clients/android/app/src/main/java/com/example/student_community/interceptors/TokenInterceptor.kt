package com.example.student_community.interceptors

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.student_community.models.user.JwtToken
import com.example.student_community.ui.auth.AuthActivity
import com.example.student_community.utility.GlobalApp
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: JwtToken?
        var req = chain.request()
        val preference =
            GlobalApp.getAppContext().getSharedPreferences("api_token", Context.MODE_PRIVATE)
        val tokenString = preference.getString("token", null)
        if (tokenString != null) {
            Log.i("OkHttp", "Token shared preference'dan okundu")
            token = Gson().fromJson(tokenString, JwtToken::class.java)
            req =
                req.newBuilder().addHeader("Authorization", "Bearer ${token.AccessToken}").build()
        }

        val res = chain.proceed(req)
        if (res.code == 401) {
            Log.i("OkHttp", "AccessToken geçersiz 401 durumuna düştü")
            var intent =Intent(GlobalApp.getAppContext(),AuthActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            GlobalApp.getAppContext().startActivity(intent)

        }
        return res
    }
}