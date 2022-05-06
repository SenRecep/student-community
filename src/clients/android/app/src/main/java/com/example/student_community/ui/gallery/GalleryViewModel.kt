package com.example.student_community.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    var latitude: MutableLiveData<String> = MutableLiveData()
    var longitude: MutableLiveData<String> = MutableLiveData()
    var address: MutableLiveData<String> = MutableLiveData()
    var fark: MutableLiveData<String> = MutableLiveData()
}