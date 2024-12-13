package com.project.skinalyze.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.skinalyze.model.UserModel
import com.project.skinalyze.repository.UserRepository
import com.project.skinalyze.ui.Event

class MainViewModel(private val repository: UserRepository): ViewModel() {
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }
}