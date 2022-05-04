package com.example.student_community.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_community.models.api.ApiError
import com.example.student_community.models.user.UserSignIn
import com.example.student_community.services.apiServices.AuthService
import com.example.student_community.utility.IViewModelState
import com.example.student_community.utility.LoadingState
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()

    fun signIn(signIn: UserSignIn): LiveData<Boolean> {
        loadingState.value = LoadingState.Loading
        var status = MutableLiveData<Boolean>()
        viewModelScope.launch {
            var apiResponse = AuthService.signIn(signIn)
            status.value=apiResponse.isSuccusful
            if (!apiResponse.isSuccusful)
                errorState.value=apiResponse.error
            loadingState.value=LoadingState.Loaded
        }
        return status
    }


}