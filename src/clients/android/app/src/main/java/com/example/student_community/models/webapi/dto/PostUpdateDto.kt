package com.example.student_community.models.webapi.dto

import com.google.gson.annotations.SerializedName

data class PostUpdateDto(
    @SerializedName("title") val Title: String?=null,
    @SerializedName("content") val Content: String?=null,
    @SerializedName("address") val Address: AddressUpdateDto?=null,
)