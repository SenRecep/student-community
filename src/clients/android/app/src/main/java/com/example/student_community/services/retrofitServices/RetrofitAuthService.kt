package com.example.student_community.services.retrofitServices

import com.example.student_community.models.user.JwtToken
import com.example.student_community.models.user.UserSignIn
import com.example.student_community.models.user.UserSignUp
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitAuthService {
    @POST("api/users/create")
    suspend fun signUp(@Body model: UserSignUp): Response<ResponseBody>

    @POST("api/auth/login")
    suspend fun signIn(@Body model: UserSignIn): Response<JwtToken>
}