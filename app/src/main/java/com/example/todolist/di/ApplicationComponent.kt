package com.example.todolist.di

import android.content.Context
import com.example.todolist.presentation.ui.MainActivity
import com.example.todolist.presentation.ui.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(mainFragment: MainFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}