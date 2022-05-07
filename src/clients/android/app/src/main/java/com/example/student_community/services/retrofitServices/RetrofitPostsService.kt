package com.example.student_community.services.retrofitServices

import com.example.student_community.models.webapi.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitPostsService {
    @GET("api/posts")
    suspend fun getPosts(@Query("skip") skip:Number,@Query("take") take:Number=5): Response<ArrayList<Post>>
}