package com.example.student_community.utility

import android.content.Context
import android.widget.Toast
import com.example.student_community.R
import com.example.student_community.exceptions.OfflineException
import com.example.student_community.models.api.ApiError
import com.example.student_community.models.api.ApiResponse
import com.example.student_community.models.user.JwtToken
import com.google.gson.Gson
import retrofit2.Response
import java.util.*

class HelperService {
    companion object {
        fun saveTokenSharedPreference(jwtToken: JwtToken) {
            var preference =
                GlobalApp.getAppContext().getSharedPreferences("api_token", Context.MODE_PRIVATE);
            var editor = preference.edit();
            editor.putString("token", Gson().toJson(jwtToken));
            editor.apply();
        }

        fun removeTokenSharedPreference() {
            var preference =
                GlobalApp.getAppContext().getSharedPreferences("api_token", Context.MODE_PRIVATE);
            var editor = preference.edit();
            editor.remove("token");
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
            var errorMessage: String = if (ex is OfflineException)
                resources.getString(R.string.ex_offline_exception)
            else
                resources.getString(R.string.ex_common_error)
            var error =
                ApiError(StatusCode = 500, Message = errorMessage, IsShow = true);
            return ApiResponse(false, error = error);
        }

        fun showErrorMessageByToast(apiError: ApiError?) {
            if (apiError == null) return;
            var errorBuilder = StringBuilder()
            if (apiError.Message != null)
                errorBuilder.append("${apiError.Message}\n")
            if (apiError.Error != null)
                errorBuilder.append("${apiError.Error!!}\n")

            if (errorBuilder.isEmpty())
                errorBuilder.append("Bir hata meydana geldi l??tfen daha sonra tekrar deneyiniz")
            Toast.makeText(GlobalApp.getAppContext(), errorBuilder.toString(), Toast.LENGTH_LONG)
                .show()
        }
    }
}