package com.project.skinalyze.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.project.skinalyze.data.retrofit.ApiConfig
import com.project.skinalyze.model.UserPreferences
import com.project.skinalyze.repository.UserRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = UserPreferences.getInstance(context.dataStore)
        val preferences = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService, preferences)
    }
}