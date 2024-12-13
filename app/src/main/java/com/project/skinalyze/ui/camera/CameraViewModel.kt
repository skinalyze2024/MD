package com.project.skinalyze.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.skinalyze.data.response.PredictionResponse
import com.project.skinalyze.model.UserModel
import com.project.skinalyze.repository.UserRepository
import com.project.skinalyze.ui.Event
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class CameraViewModel(private val repository: UserRepository): ViewModel() {
    val predictionResponse: LiveData<PredictionResponse> = repository.predictionResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun uploadImage(token: String, image: MultipartBody.Part) {
        viewModelScope.launch {
            repository.uploadImage(token, image)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

}