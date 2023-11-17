package com.example.todolist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ActivityNavigator
import androidx.navigation.findNavController
import com.example.todolist.R
import com.example.todolist.TodoApplication
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.presentation.viewmodel.MainViewModel
import com.example.todolist.presentation.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        (application as TodoApplication).applicationComponent.inject(this)
        mainViewModel= ViewModelProvider(this,mainViewModelFactory).get(MainViewModel::class.java)

    }

    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}