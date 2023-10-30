package com.example.todolist.data.repositoryimpl

import com.example.todolist.data.datasource.TodoDatabase
import com.example.todolist.domain.model.TodoList
import com.example.todolist.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor (val database: TodoDatabase): TodoRepository {

    override suspend fun getTodos():List<TodoList>{
      return  database.todoListDao().getTodos()
    }

    override suspend fun insertTodo(todoList: TodoList){
        database.todoListDao().insertTodo(todoList)
    }

    override suspend fun deleteTodo(todoList: TodoList){
        database.todoListDao().deleteTodo(todoList)
    }

}