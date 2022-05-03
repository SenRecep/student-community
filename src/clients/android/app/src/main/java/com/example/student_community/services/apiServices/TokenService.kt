package com.example.student_community.services.apiServices

import android.content.Context
import com.example.student_community.consts.ApiConsts
import com.example.student_community.models.api.ApiResponse
import com.example.student_community.models.user.Introspec
import com.example.student_community.models.user.JwtToken
import com.example.student_community.services.retrofitServices.ApiClient
import com.example.student_community.services.retrofitServices.RetrofitTokenService
import com.example.student_community.utility.GlobalApp
import com.google.gson.Gson

class TokenService {
    companion object{
        private var retrofitService =
            ApiClient.buildService(ApiConsts.webApiBaseUrl, RetrofitTokenService::class.java, true);
        suspend fun checkToken():ApiResponse<Unit>{
            var preference= GlobalApp.getAppContext().getSharedPreferences("apiToken", Context.MODE_PRIVATE);
            var tokenString: String? =
                preference.getString("token",null) ?: return ApiResponse(false);
            var token :JwtToken=Gson().fromJson(tokenString,JwtToken::class.java);
            var response= retrofitService.checkToken(token.AccessToken);
            if (!response.isSuccessful) return  ApiResponse(false);
            var introspec = response.body() as Introspec;
            return  ApiResponse(introspec.Active);
        }
    }
}