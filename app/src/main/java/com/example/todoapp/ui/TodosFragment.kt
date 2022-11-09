package com.example.todoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.FragmentTodosBinding
import com.example.todoapp.databinding.TodoItemBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoDatabase


class TodosFragment : Fragment() {

    private lateinit var binding: FragmentTodosBinding
    private val adapter = TodosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.todoList.adapter = this.adapter

        val todos = TodoDatabase(requireContext())
            .todoDao()
            .getAll()

        adapter.data = todos as MutableList<Todo>

        binding.addNewTodo.setOnClickListener {
            findNavController().navigate(TodosFragmentDirections.actionTodosFragmentToNewTodoFragment())
        }

    }

    inner class TodosViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var todo: Todo

        fun bind(todo: Todo) {
            this.todo = todo
            binding.textViewTitle.text = todo.title
            binding.textViewDescription.text = todo.description
            binding.checkBox.isChecked = todo.isDone
            }
        }

    inner class TodosAdapter(todos: List<Todo> = mutableListOf()) :
        RecyclerView.Adapter<TodosViewHolder>() {

        var data: MutableList<Todo> = mutableListOf()

        init {
            data.addAll(todos)
        }


        fun add(contact: Todo) {
            data.add(contact)
            notifyItemInserted(data.lastIndex)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = TodoItemBinding.inflate(layoutInflater, parent, false)
            return TodosViewHolder(binding)
        }

        override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
            val todo= data[position]
            holder.bind(todo)
        }

        override fun getItemCount(): Int {
            return  data.size
        }

    }

}