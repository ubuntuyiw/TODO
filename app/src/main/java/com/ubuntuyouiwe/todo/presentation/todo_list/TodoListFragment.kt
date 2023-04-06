package com.ubuntuyouiwe.todo.presentation.todo_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentTodoListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment: Fragment(R.layout.fragment_todo_list) {

    private var binding: FragmentTodoListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTodoListBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}