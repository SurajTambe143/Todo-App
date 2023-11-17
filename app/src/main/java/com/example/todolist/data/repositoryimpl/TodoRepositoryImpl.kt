package com.example.todolist.data.repositoryimpl

import android.util.Log
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

    suspend fun updateTodo(todoList: TodoList,id:Int?){
        Log.e("id Check", "updateTodo: having ID ${todoList.id} ", )
        Log.e("id Check", "updateTodo: having ID $id ")
        database.todoListDao().updateTodo(id,todoList.title,todoList.description)
    }

}