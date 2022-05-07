package com.example.student_community.models.webapi

import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @SerializedName("id") val Id: Number,
    @SerializedName("createdTime") val CreatedTime: Date,
    @SerializedName("updatedTime") val UpdatedTime: Date,
    @SerializedName("isDeleted") val IsDeleted: Boolean,
    @SerializedName("title") val Title: String,
    @SerializedName("content") val Content: String,
    @SerializedName("address") val Address: Address,
) {
    companion object {
        fun createEmptyPost(): Post {
            return Post(
                Id = 0,
                CreatedTime = Date(),
                UpdatedTime = Date(),
                IsDeleted = false,
                Title = "",
                Content = "",
                Address = Address.createEmptyAddress()
            )
        }
    }
}

