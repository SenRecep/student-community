package com.example.student_community.ui.posts.postsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_community.models.api.ApiError
import com.example.student_community.models.api.Pager
import com.example.student_community.models.webapi.Post
import com.example.student_community.services.apiServices.PostsService
import com.example.student_community.utility.IViewModelState
import com.example.student_community.utility.LoadingState
import kotlinx.coroutines.launch

class PostsListViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()
    var posts: MutableLiveData<ArrayList<Post>> = MutableLiveData()

    fun getPosts(pager: Pager) {
        loadingState.value = LoadingState.Loading
        viewModelScope.launch {
            var response= PostsService.getPosts(pager)
            if (!response.isSuccusful)
                errorState.value= response.error
            else
                posts.value= response.data!!
            loadingState.value= LoadingState.Loaded
        }
    }
}