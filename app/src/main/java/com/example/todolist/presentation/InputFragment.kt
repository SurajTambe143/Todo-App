package com.example.todolist.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import com.example.todolist.R
import com.example.todolist.adapter.TodoListAdapter
import com.example.todolist.databinding.FragmentInputBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InputFragment : Fragment() {
    lateinit var database: TodoDatabase
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    private val adapter= TodoListAdapter(){
        lifecycleScope.launch(Dispatchers.IO) {
            database.todoListDao().updateTodo(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = TodoDatabase.getDatabase(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            val title = binding.title.editableText.toString()
            val description = binding.txtDescription.editText!!.editableText.toString()
            if ((title=="")&&(description=="")){
                Snackbar.make(binding.root,"Please enter the given field",Snackbar.LENGTH_SHORT)
            }else{
                lifecycleScope.launch(Dispatchers.IO) {
                    database.todoListDao()
                        .insertTodo(TodoList(title, description))
                }
                findNavController().navigate(R.id.action_inputFragment_to_mainFragment)
            }
        }
    }
}