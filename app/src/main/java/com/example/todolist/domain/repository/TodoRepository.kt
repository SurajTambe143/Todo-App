package com.example.todolist.domain.repository

import com.example.todolist.domain.model.TodoList

interface TodoRepository {

    suspend fun getTodos():List<TodoList>

    suspend fun insertTodo(todoList: TodoList)

    suspend fun deleteTodo(todoList: TodoList)

}