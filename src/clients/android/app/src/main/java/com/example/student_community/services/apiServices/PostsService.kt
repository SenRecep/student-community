package com.example.student_community.services.apiServices

import com.example.student_community.consts.ApiConsts
import com.example.student_community.models.api.ApiResponse
import com.example.student_community.models.api.Pager
import com.example.student_community.models.webapi.Post
import com.example.student_community.services.retrofitServices.ApiClient
import com.example.student_community.services.retrofitServices.RetrofitPostsService
import com.example.student_community.utility.HelperService

class PostsService {
    companion object{
        private var retrofitService =
            ApiClient.buildService(ApiConsts.webApiBaseUrl, RetrofitPostsService::class.java, true);
        suspend fun getPosts(pager: Pager):ApiResponse<ArrayList<Post>> {
            try {
                var response = retrofitService.getPosts(pager.skip,pager.take);
                if (!response.isSuccessful) return HelperService.handleApiError(response);
                var posts = response.body()!!;
                return ApiResponse(true, posts);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }
    }

}