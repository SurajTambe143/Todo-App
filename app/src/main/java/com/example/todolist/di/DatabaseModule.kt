package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.datasource.TodoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideTodoDatabase(context:Context):TodoDatabase{
        return Room.databaseBuilder(context, TodoDatabase::class.java,"todo").build()
    }
}