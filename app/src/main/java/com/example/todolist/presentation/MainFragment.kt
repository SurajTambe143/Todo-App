package com.example.todolist.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todolist.R
import com.example.todolist.adapter.TodoListAdapter
import com.example.todolist.databinding.FragmentInputBinding
import com.example.todolist.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter=TodoListAdapter(){
        lifecycleScope.launch(Dispatchers.IO) {
            TodoDatabase.getDatabase(requireContext()).todoListDao().deleteTodo(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRv.layoutManager= StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.mainRv.adapter=adapter

        TodoDatabase.getDatabase(requireContext()).todoListDao().getTodos()
            .observe(requireActivity(), Observer {
                Log.e("Check", it.toString())
                adapter.updateList(it)
            })

        binding.fbtnNewInput.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_inputFragment)
        }

    }
}