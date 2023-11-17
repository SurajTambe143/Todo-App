package com.example.todolist.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.domain.model.TodoList


@Dao
interface TodoListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoList: TodoList)

    @Delete
    suspend fun deleteTodo(todoList: TodoList)

    @Query("UPDATE todo Set title=:title ,description=:description WHERE id=:id")
    suspend fun updateTodo(id: Int?,title:String?,description:String?)

    @Query("SELECT * FROM todo")
    suspend fun getTodos() : List<TodoList>

}