package com.example.todoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentTodosBinding


class TodosFragment : Fragment() {

    private lateinit var binding: FragmentTodosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewTodo.setOnClickListener {
            findNavController().navigate(TodosFragmentDirections.actionTodosFragmentToNewTodoFragment())
        }

    }

}