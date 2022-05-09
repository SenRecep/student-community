package com.example.student_community.services.retrofitServices

import com.example.student_community.models.webapi.Post
import com.example.student_community.models.webapi.dto.PostCreateDto
import com.example.student_community.models.webapi.dto.PostUpdateDto
import retrofit2.Response
import retrofit2.http.*

interface RetrofitPostsService {
    @GET("api/posts/pager")
    suspend fun getPosts(@Query("skip") skip:Number,@Query("take") take:Number=5): Response<ArrayList<Post>>

    @GET("api/posts/{id}")
    suspend fun getPostById(@Path("id") id:Int): Response<Post>

    @GET("api/posts/checkowner/{id}")
    suspend fun getCheckOwner(@Path("id") id:Int): Response<Boolean>

    @POST("api/posts")
    suspend fun createPost(@Body post:PostCreateDto): Response<Post>

    @PUT("api/posts/{id}")
    suspend fun updatePost(@Path("id") id:Int,@Body post:PostUpdateDto): Response<Post>

    @DELETE("api/posts/{id}")
    suspend fun deletePost(@Path("id") id:Int): Response<Post>

}