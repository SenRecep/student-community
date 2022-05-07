package com.example.student_community.models.webapi

import com.google.gson.annotations.SerializedName
import java.util.*

data class Address(
    @SerializedName("id") val Id: Number,
    @SerializedName("createdTime") val CreatedTime: Date,
    @SerializedName("updatedTime") val UpdatedTime: Date,
    @SerializedName("isDeleted") val IsDeleted: Boolean,
    @SerializedName("latitude") val Latitude: Float,
    @SerializedName("longitude") val Longitude: Float,
    @SerializedName("address") val Address: String,

    ) {
    companion object {
        fun createEmptyAddress(): Address {
            return Address(
                Id = 0,
                CreatedTime = Date(),
                UpdatedTime = Date(),
                IsDeleted = false,
                Latitude = 0f,
                Longitude = 0f,
                Address = ""
            )
        }
    }
}