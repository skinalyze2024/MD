package com.project.skinalyze.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.skinalyze.data.response.RegisterResponse
import com.project.skinalyze.repository.UserRepository
import com.project.skinalyze.ui.Event
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    val registerResponse: LiveData<RegisterResponse> = repository.registerResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast
    fun registerUser(username: String, password: String, name: String) {
        viewModelScope.launch {
            repository.registerUser(username, password, name)
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            repository.logoutUser()
        }
    }
}