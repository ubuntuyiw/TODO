package com.ubuntuyouiwe.todo.presentation.edit_or_add_todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentEditOrAddTodoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditOrAddTodo: Fragment(R.layout.fragment_edit_or_add_todo) {

    private var binding: FragmentEditOrAddTodoBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditOrAddTodoBinding.inflate(inflater,container,false)
        return  binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}