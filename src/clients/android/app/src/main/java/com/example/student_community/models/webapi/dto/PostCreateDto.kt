package com.example.student_community.models.webapi.dto

import com.google.gson.annotations.SerializedName

data class PostCreateDto(
    @SerializedName("title") val Title: String,
    @SerializedName("content") val Content: String,
    @SerializedName("userId") val UserId: Number,
    @SerializedName("address") val Address: AddressCreateDto,
)