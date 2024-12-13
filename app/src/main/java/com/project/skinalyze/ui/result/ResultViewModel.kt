package com.project.skinalyze.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.skinalyze.data.response.PredictionResult
import com.project.skinalyze.repository.UserRepository

class ResultViewModel (private val repository: UserRepository) : ViewModel() {
    val predictionResult: LiveData<PredictionResult> = repository.predictionResult
}