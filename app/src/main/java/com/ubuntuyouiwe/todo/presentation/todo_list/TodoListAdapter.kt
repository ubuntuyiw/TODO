package com.ubuntuyouiwe.todo.presentation.todo_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ubuntuyouiwe.todo.databinding.TodoRowBinding
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TodoListAdapter : PagingDataAdapter<TodoDomain, TodoListAdapter.ListTodoAdapter>(COMPARATOR) {

    inner class ListTodoAdapter(
        private val binding: TodoRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindMovie(item: TodoDomain) = with(binding) {
            binding.titleTextView.text = item.title

            val date = Date(item.deadline!!.seconds * 1000 + item.deadline.nanoseconds / 1000000)

            val formatter = SimpleDateFormat("yyyy MMM dd HH:mm", Locale.getDefault())
            val formattedDate = formatter.format(date)
            binding.deadlineTextView.text = formattedDate.toString()

        }

    }


    object COMPARATOR : DiffUtil.ItemCallback<TodoDomain>() {
        override fun areItemsTheSame(
            oldItem: TodoDomain,
            newItem: TodoDomain
        ): Boolean {

            return oldItem.uuID == newItem.uuID

        }

        override fun areContentsTheSame(
            oldItem: TodoDomain,
            newItem: TodoDomain
        ): Boolean {

            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ListTodoAdapter, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bindMovie(it)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTodoAdapter {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoRowBinding.inflate(inflater, parent, false)
        return ListTodoAdapter(binding)
    }
}