package com.example.student_community.ui.posts.postsAdd

import android.location.Location
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_community.models.api.ApiError
import com.example.student_community.models.webapi.dto.PostCreateDto
import com.example.student_community.services.LocationData
import com.example.student_community.services.LocationService
import com.example.student_community.services.apiServices.PostsService
import com.example.student_community.utility.GlobalApp
import com.example.student_community.utility.IViewModelState
import com.example.student_community.utility.LoadingState
import kotlinx.coroutines.launch

class PostsAddViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()
    var locationData: MutableLiveData<LocationData> = MutableLiveData()
    lateinit var locationService: LocationService
    fun findLocation() {
        loadingState.value = LoadingState.Loading
        locationService.getCurrentLocation().addOnCompleteListener() {
            val location: Location? = it.result
            if (location == null) {
                Toast.makeText(GlobalApp.getAppContext(), "Null Received", Toast.LENGTH_SHORT)
                    .show()
            } else {
                locationData.value = LocationData(
                    location.latitude, location.longitude, locationService.getCompleteAddressString(
                        location.latitude,
                        location.longitude,
                        GlobalApp.getAppContext()
                    )
                )
                loadingState.value = LoadingState.Loaded
            }
        }
    }

    fun requestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        viewModelScope.launch {
            locationService.requestPermissionsResult(requestCode, grantResults)
        }
    }

    suspend fun createPost(postCreateDto: PostCreateDto):LiveData<Boolean>{
        loadingState.value = LoadingState.Loading
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch {
            var response = PostsService.createPost(postCreateDto)
            result.value=response.isSuccessful
            if (!response.isSuccessful) {
                errorState.value=response.error
            loadingState.value=LoadingState.Loaded
            }
        }
        return result

    }
}