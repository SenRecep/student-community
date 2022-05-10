package com.example.student_community.ui.posts.postsDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_community.models.api.ApiError
import com.example.student_community.models.webapi.Post
import com.example.student_community.services.apiServices.PostsService
import com.example.student_community.utility.IViewModelState
import com.example.student_community.utility.LoadingState
import kotlinx.coroutines.launch

class PostsDetailViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()
    var post: MutableLiveData<Post> = MutableLiveData()
    var isOwner: MutableLiveData<Boolean> = MutableLiveData()

    fun getPost(post_id: Int) {
        loadingState.value = LoadingState.Loading
        viewModelScope.launch {
            var response = PostsService.getPostById(post_id)
            if (response.isSuccessful)
                post.value = response.data!!
            else
                errorState.value = response.error
            loadingState.value = LoadingState.Loaded
        }
    }

    fun checkOwner(postId: Int) {
        viewModelScope.launch {
            var response = PostsService.getCheckOwner(postId)
            if (response.isSuccessful)
                isOwner.value = response.data!!
        }
    }

    fun deletePost(postId: Int):LiveData<Boolean>  {
        loadingState.value = LoadingState.Loading
        var status=MutableLiveData<Boolean>()
        viewModelScope.launch {
            var response = PostsService.deletePost(postId)
            if (response.isSuccessful)
                status.value=true
            else
                errorState.value = response.error
            loadingState.value = LoadingState.Loaded
        }
        return  status
    }

}