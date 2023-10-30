package com.example.todolist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.model.TodoList
import com.example.todolist.data.repositoryimpl.TodoRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor (val repository: TodoRepositoryImpl):ViewModel() {

    private val todoLiveData = MutableLiveData<List<TodoList>>()

    val songs: LiveData<List<TodoList>>
        get() = todoLiveData

    fun getTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            todoLiveData.postValue(repository.getTodos())
        }
    }

    fun insertTodo(todoList: TodoList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTodo(todoList)
        }
    }

    fun deleteTodo(todoList: TodoList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todoList)
        }
    }

}