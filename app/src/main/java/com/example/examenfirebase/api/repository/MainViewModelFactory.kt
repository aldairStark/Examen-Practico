package com.example.examenfirebase.api.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examenfirebase.api.FieldViewModel

class MainViewModelFactory(private val repository: RepositoryField):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FieldViewModel(repository) as T
    }
}