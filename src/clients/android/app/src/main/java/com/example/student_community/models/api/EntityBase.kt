package com.example.student_community.models.api

import com.google.gson.annotations.SerializedName
import java.util.*

 data class EntityBase(
    @SerializedName("id") val Id: Number,
    @SerializedName("createdTime") val CreatedTime: Date,
    @SerializedName("updatedTime") val UpdatedTime: Date,
    @SerializedName("isDeleted") val IsDeleted: Boolean,
)