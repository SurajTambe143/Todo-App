package com.example.todolist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoList(
    val title: String?="",
    val description:String?=""
){
    @PrimaryKey(autoGenerate = true)
    var id: Int =0
}
