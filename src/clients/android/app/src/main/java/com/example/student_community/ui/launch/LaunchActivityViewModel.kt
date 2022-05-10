package com.example.student_community.ui.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_community.models.api.ApiError
import com.example.student_community.services.apiServices.TokenService
import com.example.student_community.utility.IViewModelState
import com.example.student_community.utility.LoadingState
import kotlinx.coroutines.launch

class LaunchActivityViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()

    fun tokenCheck(): LiveData<Boolean> {
        loadingState.value = LoadingState.Loading
        var status = MutableLiveData<Boolean>()
        viewModelScope.launch {
            var res= TokenService.checkToken()
            status.value=res.isSuccessful
            loadingState.value=LoadingState.Loaded
            if (!res.isSuccessful) errorState.value=res.error
        }
        return status
    }
}