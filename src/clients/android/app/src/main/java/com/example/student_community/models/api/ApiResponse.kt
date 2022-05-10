package com.example.student_community.models.api

data class ApiResponse<T>(var isSuccessful: Boolean, var data: T? = null, var error: ApiError? = null)