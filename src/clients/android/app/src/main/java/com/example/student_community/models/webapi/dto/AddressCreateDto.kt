package com.example.student_community.models.webapi.dto

import com.google.gson.annotations.SerializedName

data class AddressCreateDto(
    @SerializedName("latitude") val Latitude: Float,
    @SerializedName("longitude") val Longitude: Float,
    @SerializedName("address") val Address: String,
)