package com.example.todolist.presentation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface TodoListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoList: TodoList)

    @Delete
    suspend fun deleteTodo(todoList: TodoList)

    @Update
    suspend fun updateTodo(todoList: TodoList)

    @Query("SELECT * FROM todo")
    fun getTodos() : LiveData<List<TodoList>>

}