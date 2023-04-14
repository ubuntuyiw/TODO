package com.ubuntuyouiwe.todo.presentation.edit_or_add_todo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentEditOrAddTodoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class EditOrAddTodoFragment : Fragment(R.layout.fragment_edit_or_add_todo) {

    private var binding: FragmentEditOrAddTodoBinding? = null

    private val notificationState = ArrayList<String>()
    private lateinit var notificationStateAdapter: ArrayAdapter<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditOrAddTodoBinding.inflate(inflater, container, false)
        return binding!!.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val viewModel: EditOrAddTodoViewModel by hiltNavGraphViewModels(R.id.nav_graph)
        val viewModel: EditOrAddTodoViewModel by viewModels()


        val bundle: EditOrAddTodoFragmentArgs by navArgs()

        binding!!.titleEditText.setText(viewModel.title.value)
        binding!!.contentEditText.setText(viewModel.content.value)


        val deadline = Date(viewModel.deadline.value!!.seconds * 1000 + viewModel.deadline.value!!.nanoseconds / 1000000)

        val formatter = SimpleDateFormat("yyyy MMM dd HH:mm", Locale.getDefault())
        val formattedDate = formatter.format(deadline)
        
        binding!!.deadlineTextView.text = formattedDate




        notificationState.add("Every Day")
        notificationState.add("Every Week")
        notificationState.add("Monthly")
        notificationState.add("Every Year")

        notificationStateAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_2,
            android.R.id.text2,
            notificationState
        )


        binding!!.materialToolbar.title = "Edit"
        binding!!.materialToolbar.inflateMenu(R.menu.edit_or_add_menu)


        binding!!.materialToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.actionTodoSave -> {
                    viewModel.title.value = binding!!.titleEditText.text.toString()
                    viewModel.content.value = binding!!.contentEditText.text.toString()

                    viewModel.event(EditOrAddEvent.SaveTodo)

                    true
                }

                R.id.actionTodoDelete -> {

                    true
                }


                else -> false

            }
        }




        val timeFormat = SimpleDateFormat("yyyy MMM dd HH:mm", Locale.getDefault())

        val date = Timestamp.now().toDate()
        val calendar = Calendar.getInstance()
        calendar.time = date

        binding!!.deadlineLine.setOnClickListener {

            datePickerDialog(calendar) {view, year, month, dayOfMonth ->
                timePickerDialog(calendar, year, month, dayOfMonth, timeFormat)
            }

        }
    }

    private fun datePickerDialog(
        calendar: Calendar,
        click: (DatePicker,Int,Int,Int) -> Unit
    ) {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.AppDatePickerDialogTheme,
            { view, year, month, dayOfMonth ->
                click(view, year, month, dayOfMonth)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.setTitle("Tarih Seçiniz")
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ayarlar", datePickerDialog)
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "iptal", datePickerDialog)
        datePickerDialog.show()

    }

    private fun timePickerDialog(
        calendar: Calendar,
        year: Int,
        month: Int,
        dayOfMonth: Int,
        simpleDateFormat: SimpleDateFormat
    ) {
        val viewModel: EditOrAddTodoViewModel by hiltNavGraphViewModels(R.id.nav_graph)

        val timePicker = TimePickerDialog(
            requireContext(),
            R.style.AppTimePickerDialogTheme,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                val formattedTime = simpleDateFormat.format(calendar.time)

                binding!!.deadlineTextView.text = formattedTime

                val selectedDate = calendar.time

                val firebaseTimestamp = Timestamp(selectedDate)

                viewModel.deadline.value = firebaseTimestamp

            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )

        timePicker.setTitle("Saat Seçiniz")
        timePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Ayarlar", timePicker)
        timePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "iptal", timePicker)
        timePicker.show()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}