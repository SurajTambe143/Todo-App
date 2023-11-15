package com.example.todolist.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todolist.R
import com.example.todolist.TodoApplication
import com.example.todolist.adapter.TodoListAdapter
import com.example.todolist.data.datasource.TodoDatabase
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.data.repositoryimpl.TodoRepositoryImpl
import com.example.todolist.presentation.viewmodel.MainViewModel
import com.example.todolist.presentation.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {


    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = TodoListAdapter() {
        mainViewModel.deleteTodo(it)
        //observeViewModel()
        mainViewModel.getTodos()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TodoApplication).applicationComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRv.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.mainRv.adapter = adapter
        adapter.onItemClick = {
            val action = MainFragmentDirections.actionMainFragmentToInputFragment(it)
            findNavController().navigate(action)
        }
        setupViewModel()
        setUpUI()

    }

    private fun setUpUI() {
        binding.fbtnNewInput.setOnClickListener {
            // val action=MainFragmentDirections.actionMainFragmentToInputFragment(null)
            findNavController().navigate(R.id.action_mainFragment_to_inputFragment)
        }
    }

    private fun setupViewModel() {
//        val todoDatabase = TodoDatabase.getDatabase(requireContext())
//        val repository = TodoRepositoryImpl(todoDatabase)

        mainViewModel =
            ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.getTodos()

        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.songs.observe(viewLifecycleOwner) {
            adapter.updateList(it)
            Log.e("Test", it.toString())
        }
    }
}