package com.example.student_community.services.retrofitServices

import com.example.student_community.interceptors.NetworkInterceptor
import com.example.student_community.interceptors.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun <T> buildService(
            baseUrl: String,
            retrofitService: Class<T>,
            existInterceptor: Boolean
        ): T {
            var clientBuilder = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor(NetworkInterceptor())
                .addInterceptor(TokenInterceptor());
            if (existInterceptor) {
                //interceptor eklenecek
            }
            return Retrofit.Builder().baseUrl(baseUrl).client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create()).build().create(retrofitService);
        }
    }
}