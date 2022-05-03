package com.example.student_community.services.retrofitServices

import com.example.student_community.models.user.Introspec
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitTokenService {
    @FormUrlEncoded
    @POST("auth/introspect")
    suspend fun checkToken(
        @Field("token") token: String, @Header("Authorization") authorization: String
    ): Response<Introspec>
}