package com.project.skinalyze.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.skinalyze.data.response.HistoryResponseItem
import com.project.skinalyze.model.UserModel
import com.project.skinalyze.repository.UserRepository
import com.project.skinalyze.ui.Event
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: UserRepository) : ViewModel()  {
    val historyResponse: LiveData<List<HistoryResponseItem>> = repository.historyResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val textToast: LiveData<Event<String>> = repository.textToast

    fun getHistory(token: String) {
        viewModelScope.launch {
            repository.getHistory(token)
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getUser()
    }
}