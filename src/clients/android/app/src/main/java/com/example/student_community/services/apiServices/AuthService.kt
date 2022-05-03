package com.example.student_community.services.apiServices

import com.example.student_community.consts.ApiConsts
import com.example.student_community.services.retrofitServices.ApiClient
import com.example.student_community.services.retrofitServices.RetrofitAuthService

class AuthService {
    companion object {
        private var retrofitService =
            ApiClient.buildService(ApiConsts.webApiBaseUrl, RetrofitAuthService::class.java, false);
        /*
        suspend fun signUp():ApiResponse<NoContent>{
            var res= retrofitService.signUp()
        }
        */

    }
}