package com.example.student_community.utility

import android.content.Context
import com.example.student_community.R
import com.example.student_community.exceptions.OfflineException
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

        fun <T> handleException(ex: Exception): ApiResponse<T> {
            val resources = GlobalApp.getAppContext().resources;
            var errorMessage:ArrayList<String> = if (ex is OfflineException)
                arrayListOf(resources.getString(R.string.ex_offline_exception));
            else
                arrayListOf(resources.getString(R.string.ex_common_error));
            var error =
                ApiError(StatusCode = 500, Message = errorMessage, IsShow = true);
            return ApiResponse(false, error = error);
        }
    }
}