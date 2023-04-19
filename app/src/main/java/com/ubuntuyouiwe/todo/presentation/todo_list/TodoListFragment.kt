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
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentTodoListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoListFragment : Fragment(R.layout.fragment_todo_list) {
    private lateinit var todoListAdapter: TodoListAdapter
    override fun onStart() {
        super.onStart()

        //todoListAdapter.refresh()
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

        binding!!.refresh.setOnRefreshListener {
            todoListAdapter.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            todoListAdapter.loadStateFlow.collect {
                mediatorAppend(it)
                mediatorPrepend(it)
                mediatorRefresh(it)


                sourceAppend(it)
                sourcePrepend(it)
                sourceRefresh(it)

               /* if (!it.source.refresh.endOfPaginationReached)
                    todoListAdapter.retry()*/


            }
        }

        /*viewLifecycleOwner.lifecycleScope.launch {
            todoListAdapter.loadStateFlow.collect {
                if (it.mediator != null){
                    when (it.mediator?.refresh!!) {
                        is LoadState.Loading -> {
                            Log.v("loadStateDeneme","mediator : loading")

                            binding!!.progressBar.visibility = View.VISIBLE
                            binding!!.refresh.isRefreshing = true

                        }
                        is LoadState.Error -> {
                            Log.v("loadStateDeneme","mediator : Error")
                            binding!!.refresh.isRefreshing = false
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
                            Log.v("loadStateDeneme","mediator : NotLoading")

                            binding!!.refresh.isRefreshing = false
                            binding!!.progressBar.visibility = View.INVISIBLE


                        }
                    }
                }

            }
        }*/






        viewLifecycleOwner.lifecycleScope.launch {


            viewModel.state.collectLatest { todoList ->
                todoListAdapter.submitData(todoList)

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

            //findNavController().navigate(TodoListFragmentDirections.actionTodoListFragmentToEditOrAddTodoFragment())
            todoListAdapter.refresh()

        }


    }


    private fun mediatorRefresh(loadStates: CombinedLoadStates) {
        when (val refresh = loadStates.mediator?.refresh!!) {
            is LoadState.Loading -> {
                Log.v("loadStates: CombinedLoadStates","mediatorRefresh Loading")

            }
            is LoadState.NotLoading -> {
                Log.v("loadStates: CombinedLoadStates","mediatorRefresh NotLoading")

            }
            is LoadState.Error -> {
                Log.v("loadStates: CombinedLoadStates","mediatorRefresh Error")

            }
        }
    }
    private fun mediatorAppend(loadStates: CombinedLoadStates) {
        when (val append = loadStates.mediator?.append!!) {
            is LoadState.Loading -> {
                Log.v("loadStates: CombinedLoadStates","mediatorAppend Loading")

            }
            is LoadState.NotLoading -> {
                Log.v("loadStates: CombinedLoadStates","mediatorAppend NotLoading")

            }
            is LoadState.Error -> {
                Log.v("loadStates: CombinedLoadStates","mediatorAppend Error")

            }
        }
    }
    private fun mediatorPrepend(loadStates: CombinedLoadStates) {
        when (val prepend = loadStates.mediator?.prepend!!) {
            is LoadState.Loading -> {
                Log.v("loadStates: CombinedLoadStates","mediatorPrepend Loading")

            }
            is LoadState.NotLoading -> {
                Log.v("loadStates: CombinedLoadStates","mediatorPrepend NotLoading")

            }
            is LoadState.Error -> {
                Log.v("loadStates: CombinedLoadStates","mediatorPrepend Error")

            }
        }
    }


    private fun sourceRefresh(loadStates: CombinedLoadStates) {
        when (val refresh = loadStates.source.refresh) {
            is LoadState.Loading -> {
                Log.v("loadStates: CombinedLoadStates","sourceRefresh Loading")

            }
            is LoadState.NotLoading -> {
                Log.v("loadStates: CombinedLoadStates","sourceRefresh NotLoading")

            }
            is LoadState.Error -> {
                Log.v("loadStates: CombinedLoadStates","sourceRefresh Error : ${refresh.endOfPaginationReached} ${refresh.error.message.toString()}" )

            }
        }
    }
    private fun sourceAppend(loadStates: CombinedLoadStates) {
        when (val append = loadStates.source.append) {
            is LoadState.Loading -> {
                Log.v("loadStates: CombinedLoadStates","sourceAppend Loading")

            }
            is LoadState.NotLoading -> {
                Log.v("loadStates: CombinedLoadStates","sourceAppend NotLoading")

            }
            is LoadState.Error -> {
                Log.v("loadStates: CombinedLoadStates","sourceAppend Error")

            }
        }
    }
    private fun sourcePrepend(loadStates: CombinedLoadStates) {
        when (val prepend = loadStates.source.prepend) {
            is LoadState.Loading -> {
                Log.v("loadStates: CombinedLoadStates","sourcePrepend Loading")

            }
            is LoadState.NotLoading -> {
                Log.v("loadStates: CombinedLoadStates","sourcePrepend NotLoading")

            }
            is LoadState.Error -> {
                Log.v("loadStates: CombinedLoadStates","sourcePrepend Error")

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }


}