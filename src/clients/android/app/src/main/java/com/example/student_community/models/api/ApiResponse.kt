package com.example.student_community.models.api

data class ApiResponse<T>(var isSuccusful: Boolean, var data: T? = null,var error: ApiError? = null)