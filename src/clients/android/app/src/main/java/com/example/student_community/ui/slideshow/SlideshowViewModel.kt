package com.example.student_community.ui.slideshow

import androidx.lifecycle.LiveData
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

class SlideshowViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()

    fun getPosts(pager: Pager):LiveData<List<Post>> {
        loadingState.value = LoadingState.Loading
        var posts = MutableLiveData<List<Post>>()
        viewModelScope.launch {
            var response= PostsService.getPosts(pager)
            if (!response.isSuccusful)
                errorState.value= response.error
            else
                posts.value= response.data!!
            loadingState.value= LoadingState.Loaded
        }
        return  posts
    }
}