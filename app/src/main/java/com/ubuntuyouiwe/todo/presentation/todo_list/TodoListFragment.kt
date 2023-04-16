package com.ubuntuyouiwe.todo.presentation.todo_list

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentTodoListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoListFragment : Fragment(R.layout.fragment_todo_list) {
    private lateinit var todoListAdapter: TodoListAdapter
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
            todoListAdapter.loadStateFlow.collect {
                when (it.source.refresh) {
                    is LoadState.Loading -> {
                        Log.v("loadStateDeneme","loading")
                    }
                    is LoadState.Error -> {
                        Log.v("loadStateDeneme","Error")
                    }
                    is LoadState.NotLoading -> {
                        Log.v("loadStateDeneme","NotLoading")
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            todoListAdapter.loadStateFlow.collect {
                when (it.mediator?.refresh!!) {
                    is LoadState.Loading -> {
                        binding!!.progressBar.visibility = View.VISIBLE
                    }
                    is LoadState.Error -> {
                        binding!!.progressBar.visibility = View.INVISIBLE
                        Snackbar.make(binding!!.todoRecyclerList, "Bağlantı sorunu lütfen tekrar deneyin", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Retry") {
                                todoListAdapter.retry()
                            }
                            .setBackgroundTint(Color.WHITE)
                            .setTextColor(Color.BLACK)
                            .setActionTextColor(Color.BLACK)
                            .show()

                    }
                    is LoadState.NotLoading -> {
                        binding!!.progressBar.visibility = View.INVISIBLE


                    }
                }
            }
        }





        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.st.collect {
                todoListAdapter.submitData(it)
            }

        }


        todoListAdapter.setOnItemClickListener { uuID, title, content, deadline ->
            Log.v("UUID.value!!",uuID.toString())
            findNavController().navigate(

                TodoListFragmentDirections.actionTodoListFragmentToEditOrAddTodoFragment(
                    uuID = uuID?:"null",
                    title = title?:"null",
                    content = content?:"null",
                    deadline = deadline!!
                )
            )

        }


        binding!!.floatingActionButton.setOnClickListener {

            findNavController().navigate(TodoListFragmentDirections.actionTodoListFragmentToEditOrAddTodoFragment())
            //todoListAdapter.retry()

        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }


}