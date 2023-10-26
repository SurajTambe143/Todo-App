package com.example.todolist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
//        findNavController()
//        supportFragmentManager.beginTransaction().add(binding.mainFragmentContainerView.id,MainFragment()).commit()
//
//        binding.fbtnNewInput.setOnClickListener {
//            supportFragmentManager.beginTransaction().add(binding.mainFragmentContainerView.id,InputFragment()).addToBackStack(null).commit()
//        }
    }
}