package com.example.todolist.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist.R
import com.example.todolist.adapter.TodoListAdapter
import com.example.todolist.data.datasource.TodoDatabase
import com.example.todolist.databinding.FragmentInputBinding
import com.example.todolist.domain.model.TodoList
import com.example.todolist.data.repositoryimpl.TodoRepositoryImpl
import com.example.todolist.presentation.viewmodel.MainViewModel
import com.example.todolist.presentation.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar

class InputFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel
    lateinit var database: TodoDatabase
    private var _binding: FragmentInputBinding? = null

    private val args: InputFragmentArgs by navArgs()
    private val binding get() = _binding!!
//    private val adapter= TodoListAdapter(){
//        lifecycleScope.launch(Dispatchers.IO) {
//            mainViewModel.updateTodo(it)
//        }
//    }

//    private val adapter = TodoListAdapter(edit = {
//             binding.title.setText(args.inputData.title)
//            binding.description.setText(args.inputData.description)
//            updateTodoList()
//    }, remove = {
//    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        setupViewModel()
        setUpUI()
    }


    private fun setUpUI() {
        args.inputData?.title?.let {
            binding.title.setText(it)

        }
//        binding.description.setText(args.inputData.description)
        binding.btnAdd.setOnClickListener {
            val title = binding.title.editableText.toString()
            val description = binding.txtDescription.editText!!.editableText.toString()
            if ((title == "") && (description == "")) {
                Snackbar.make(binding.root, "Please enter the given field", Snackbar.LENGTH_SHORT).show()
            } else {
                mainViewModel.insertTodo(TodoList(title, description))
                findNavController().navigateUp()
            }
        }
    }

    private fun updateTodoList() {
        binding.btnAdd.setOnClickListener {
            val title = binding.title.editableText.toString()
            val description = binding.txtDescription.editText!!.editableText.toString()
            if ((title == "") && (description == "")) {
                Snackbar.make(binding.root, "Please enter the given field", Snackbar.LENGTH_SHORT).show()
            } else {
                mainViewModel.updateTodo(TodoList(title, description))
                findNavController().navigate(R.id.action_inputFragment_to_mainFragment)
            }
        }
    }

    private fun setupViewModel() {
        val todoDatabase = TodoDatabase.getDatabase(requireContext())
        val repository = TodoRepositoryImpl(todoDatabase)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }
}