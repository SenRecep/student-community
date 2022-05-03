package com.example.student_community.models.user

import com.google.gson.annotations.SerializedName

data class UserSignUp(
    @SerializedName("userName") var UserName: String,
    @SerializedName("firstName") var FirstName: String,
    @SerializedName("lastName") var LastName: String,
    @SerializedName("email") var Email: String,
    @SerializedName("password") var Password: String
)