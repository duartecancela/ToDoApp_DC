package com.example.todoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.databinding.FragmentNewTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoDatabase
import androidx.navigation.fragment.findNavController

class NewTodoFragment : Fragment() {

    private lateinit var binding: FragmentNewTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTodoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addToDo.setOnClickListener {

            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()
            val todo = Todo(title, description, false)


            TodoDatabase(requireContext())
                .todoDao()
                .insert(todo)

            findNavController().popBackStack()
        }
    }

}