package com.project.skinalyze.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.skinalyze.data.response.UpdateUserResponse
import com.project.skinalyze.data.response.UserResponse
import com.project.skinalyze.model.UserModel
import com.project.skinalyze.repository.UserRepository
import com.project.skinalyze.ui.Event
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    val updateUserResponse : LiveData<UpdateUserResponse> = repository.updateUserResponse
    val userResponse: LiveData<UserResponse> = repository.userResponse
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getUserData(token: String) {
        viewModelScope.launch {
            repository.getUserData(token)
        }
    }
    fun updateUser(token: String, avatar_image: MultipartBody.Part) {
        viewModelScope.launch {
            repository.updateUser(token, avatar_image)
        }
    }

    fun updateUserData(token: String, username: String, password: String, name: String) {
        viewModelScope.launch {
            repository.updateDataUser(token, username, password, name)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }
    fun logoutUser() {
        viewModelScope.launch {
            repository.logoutUser()
        }
    }
}