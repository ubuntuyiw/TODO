package com.ubuntuyouiwe.todo.presentation.todo_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentTodoListBinding
import com.ubuntuyouiwe.todo.presentation.component.bottom_sheet.TodoAddFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoListFragment : Fragment(R.layout.fragment_todo_list) {
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var dialog: BottomSheetDialog
    override fun onStart() {
        super.onStart()

        todoListAdapter.refresh()
    }


    private var binding: FragmentTodoListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        todoListAdapter = TodoListAdapter()

        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: TodoListViewModel by hiltNavGraphViewModels(R.id.nav_graph)




        binding?.todoRecyclerList!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding?.todoRecyclerList!!.adapter = todoListAdapter




        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.st.collect {
                todoListAdapter.submitData(it)
            }

        }

        viewLifecycleOwner.lifecycleScope.launch {
            todoListAdapter.loadStateFlow.collect {
                when (val refresh = it.refresh) {
                    is LoadState.Loading -> {
                    }

                    is LoadState.Error -> {

                    }

                    is LoadState.NotLoading -> {
                    }
                }
            }
        }


        binding!!.floatingActionButton.setOnClickListener {
            //findNavController().navigate(TodoListFragmentDirections.actionTodoListFragmentToEditOrAddTodoFragment(name = "ahmet"))
            //todoListAdapter.retry()
            val bottomSheetFragment = TodoAddFragment()
            bottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                "myBottomSheetFragment"
            )
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }


}