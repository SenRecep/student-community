package com.example.student_community.models.webapi

import android.os.Parcelable
import com.example.student_community.models.webapi.dto.PostListDto
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Post(
    @SerializedName("id") val Id: Number,
    @SerializedName("createdTime") val CreatedTime: Date,
    @SerializedName("updatedTime") val UpdatedTime: Date,
    @SerializedName("isDeleted") val IsDeleted: Boolean,
    @SerializedName("title") val Title: String,
    @SerializedName("content") val Content: String,
    @SerializedName("userId") val UserId: Number,
    @SerializedName("address") val Address: Address,
) : Parcelable {
    companion object {
        fun createEmptyPost(): Post {
            return Post(
                Id = 0,
                CreatedTime = Date(),
                UpdatedTime = Date(),
                IsDeleted = false,
                UserId = 0,
                Title = "",
                Content = "",
                Address = Address.createEmptyAddress()
            )
        }
    }

    fun parseToListDto(distanceText: String = "0m", distance: Float = 0f,dateText:String="to day"): PostListDto {
        return PostListDto(
            Id = Id,
            Title = Title,
            Content = Content,
            UpdatedTime = UpdatedTime,
            Address = Address.parseToListDto(distanceText, distance),
            UpdatedTimeText = dateText
        )
    }
}

