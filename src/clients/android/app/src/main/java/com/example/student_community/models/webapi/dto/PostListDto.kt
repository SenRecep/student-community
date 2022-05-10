package com.example.student_community.models.webapi.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class PostListDto (
    val Id: Number,
    val Title: String,
    val Content: String,
    val Address: AddressListDto,
    val UpdatedTime: Date,
    var UpdatedTimeText:String
): Parcelable