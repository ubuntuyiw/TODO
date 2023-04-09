package com.ubuntuyouiwe.todo.presentation.todo_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentTodoListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoListFragment : Fragment(R.layout.fragment_todo_list) {
    private val todoListAdapter by lazy { TodoListAdapter() }
    private var collectJob: Job? = null

    private var binding: FragmentTodoListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: TodoListViewModel by hiltNavGraphViewModels(R.id.nav_graph)

        binding?.todoRecyclerList!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding?.todoRecyclerList!!.adapter = todoListAdapter



        collectJob?.cancel()

        collectJob = viewLifecycleOwner.lifecycleScope.launch {

            viewModel.state.cachedIn(this) .collect {
                todoListAdapter.submitData(it)
            }




        }
        Log.v("denemedeneme","awd")

        viewLifecycleOwner.lifecycleScope.launch {
            todoListAdapter.loadStateFlow.collect {
                when (val refresh = it.refresh) {
                    is LoadState.Loading ->{
                        Log.v("denemedeneme","yükleniyor")
                    }
                    is LoadState.Error ->{
                        Log.v("denemedeneme",refresh.error.message.toString())

                    }
                    is LoadState.NotLoading ->{
                        Log.v("denemedeneme","notloading")
                    }
                }
            }
        }


        binding!!.button.setOnClickListener {
           findNavController().navigate(TodoListFragmentDirections.actionTodoListFragmentToEditOrAddTodoFragment())





        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}