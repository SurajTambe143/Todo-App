package com.example.todolist.presentation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoList::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoListDao(): TodoListDAO

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "todo").build()

                }
            }
            return INSTANCE!!
        }
    }
}