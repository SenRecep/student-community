package com.example.student_community.utility

import androidx.lifecycle.MutableLiveData
import com.example.student_community.models.api.ApiError

interface IViewModelState {
    var loadingState: MutableLiveData<LoadingState>
    var errorState:MutableLiveData<ApiError?>
}