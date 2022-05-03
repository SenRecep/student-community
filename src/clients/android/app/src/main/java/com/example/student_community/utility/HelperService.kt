package com.example.student_community.utility

import android.content.Context
import com.example.student_community.models.api.ApiError
import com.example.student_community.models.api.ApiResponse
import com.example.student_community.models.user.JwtToken
import com.google.gson.Gson
import retrofit2.Response

class HelperService {
    companion object {
        fun saveTokenSharedPreference(jwtToken: JwtToken) {
            var preference =
                GlobalApp.getAppContext().getSharedPreferences("api_token", Context.MODE_PRIVATE);
            var editor = preference.edit();
            editor.putString("token", Gson().toJson(jwtToken));
            editor.apply();
        }

        fun <T1, T2> handleApiError(response: Response<T1>): ApiResponse<T2> {
            var apiError: ApiError? = null;
            if (response.errorBody() != null) {
                var errorBody = response.errorBody()!!.string();
                apiError = Gson().fromJson(errorBody, ApiError::class.java);
            }
            return ApiResponse(false, null, apiError);
        }
    }
}