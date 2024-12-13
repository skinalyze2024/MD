package com.project.skinalyze.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.skinalyze.data.response.UserResponse
import com.project.skinalyze.model.UserModel
import com.project.skinalyze.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    val userResponse: LiveData<UserResponse> = repository.userResponse

    fun getUserData(token: String) {
        viewModelScope.launch {
            repository.getUserData(token)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }
}