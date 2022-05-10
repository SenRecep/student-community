package com.example.student_community.models.webapi.dto

import java.util.*

data class PostListDto (
    val Id: Number,
    val Title: String,
    val Content: String,
    val Address: AddressListDto,
    val UpdatedTime: Date,
    var UpdatedTimeText:String
)