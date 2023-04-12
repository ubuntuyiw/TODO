package com.ubuntuyouiwe.todo.presentation.component.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class EveryWeekFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_every_week, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: NotificationSharedViewModel by activityViewModels()

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        val date = Timestamp.now().toDate()
        val calendar = Calendar.getInstance()
        calendar.time = date

    }


}