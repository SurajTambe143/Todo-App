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
import com.example.todolist.utils.Operation
import com.google.android.material.snackbar.Snackbar

class InputFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel
    lateinit var database: TodoDatabase
    private var _binding: FragmentInputBinding? = null

    private val args: InputFragmentArgs by navArgs()
    private val binding get() = _binding!!

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
        updateTodoList()
        binding.icBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateTodoList() {
        args.todoOperation.let {
            if (Operation.UPDATE==it){
                args.inputData.let {
                    Log.w("id Check", "updateTodoList: ${it?.id}" )
                    val id=it?.id
                    binding.title.setText(it?.title)
                    binding.description.setText(it?.description)
                    binding.btnAdd.setOnClickListener {
                        addOrUpdateTodoOnClick(Select.UPDATE,id)
                    }
                }
            }
            if (Operation.ADD==it){
                binding.btnAdd.setOnClickListener {
                    addOrUpdateTodoOnClick(Select.ADD,0)
                }
            }
        }
    }

    private fun setupViewModel() {
        val todoDatabase = TodoDatabase.getDatabase(requireContext())
        val repository = TodoRepositoryImpl(todoDatabase)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }

    private fun addOrUpdateTodoOnClick(select: Select,id:Int?) {
        val title = binding.title.editableText.toString()
        val description = binding.description.editableText!!.toString()
        if ((title == "") && (description == "")) {
            Snackbar.make(binding.root, "Please enter the given field", Snackbar.LENGTH_SHORT)
                .show()
        } else {
            when (select) {
                Select.ADD -> {
                    Log.w("Add", "SELECT.ADD is Called")
                    mainViewModel.insertTodo(TodoList(title, description))
                }
                Select.UPDATE -> {
                    Log.w("Update","SELECT.Update Is called $title and $description" )
                    mainViewModel.updateTodo(TodoList(title, description),id)
                }
            }
            findNavController().navigateUp()
        }
    }

    enum class Select {
        ADD, UPDATE
    }
}