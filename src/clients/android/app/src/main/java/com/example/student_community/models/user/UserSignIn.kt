package com.example.student_community.models.user

import com.google.gson.annotations.SerializedName

data class UserSignIn(
    @SerializedName("userName") var UserName: String,
    @SerializedName("password") var Password: String
)