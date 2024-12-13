package com.project.skinalyze.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.skinalyze.data.response.LoginResponse
import com.project.skinalyze.model.UserModel
import com.project.skinalyze.repository.UserRepository
import com.project.skinalyze.ui.Event
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository): ViewModel() {
    val loginResponse: LiveData<LoginResponse> = repository.loginResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun loginUser (username: String, password: String) {
        viewModelScope.launch {
            repository.loginUser(username, password)
        }
    }

    fun getLoginUser(user: UserModel) {
        viewModelScope.launch {
            repository.getLoginUser(user)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }

    fun getToken() {
        viewModelScope.launch {
            repository.getToken()
        }
    }
}