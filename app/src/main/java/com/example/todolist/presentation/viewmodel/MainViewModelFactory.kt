package com.example.todolist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.todolist.data.repositoryimpl.TodoRepositoryImpl

class MainViewModelFactory(val repository: TodoRepositoryImpl):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MainViewModel(repository) as T
    }
}