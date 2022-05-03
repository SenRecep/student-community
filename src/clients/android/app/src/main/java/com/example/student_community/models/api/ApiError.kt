package com.example.student_community.models.api

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
data class ApiError(
    @SerializedName("statusCode") var StatusCode: Number,
    @SerializedName("timestamp") var Timestamp: String? = sdf.format(Date()),
    @SerializedName("path") var Path:String?=null,
    @SerializedName("message") var Message:ArrayList<String>?=null,
    @SerializedName("error") var Error:String?=null,

)