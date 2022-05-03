package com.example.student_community.services.apiServices

import com.example.student_community.consts.ApiConsts
import com.example.student_community.models.api.ApiResponse
import com.example.student_community.models.user.JwtToken
import com.example.student_community.models.user.UserSignIn
import com.example.student_community.models.user.UserSignUp
import com.example.student_community.services.retrofitServices.ApiClient
import com.example.student_community.services.retrofitServices.RetrofitAuthService
import com.example.student_community.utility.HelperService
import okhttp3.internal.Util

class AuthService {
    companion object {
        private var retrofitService =
            ApiClient.buildService(ApiConsts.webApiBaseUrl, RetrofitAuthService::class.java, false);

        suspend fun signUp(userSignUp: UserSignUp): ApiResponse<Util> {
            var response = retrofitService.signUp(userSignUp);
            if (!response.isSuccessful) return HelperService.handleApiError(response);
            return ApiResponse(true);
        }

        suspend fun signIn(userSignIn: UserSignIn): ApiResponse<Util> {
            var response = retrofitService.signIn(userSignIn);
            if (!response.isSuccessful) return HelperService.handleApiError(response);

            var token = response.body() as JwtToken;
            HelperService.saveTokenSharedPreference(token);
            return ApiResponse(true);
        }

    }
}