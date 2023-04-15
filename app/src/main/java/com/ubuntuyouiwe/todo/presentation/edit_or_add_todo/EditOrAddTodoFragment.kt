package com.ubuntuyouiwe.todo.presentation.edit_or_add_todo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
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


        binding!!.titleEditText.setText(viewModel.title.value)
        binding!!.contentEditText.setText(viewModel.content.value)
        val asd = viewModel.deadline.value


        binding!!.deadlineTextView.text =
            viewModel.deadline.value?.timestampToString() ?: "Task Completion Time"





        binding!!.efaSave.setOnClickListener {
            viewModel.title.value = binding!!.titleEditText.text.toString()
            viewModel.content.value = binding!!.contentEditText.text.toString()
            viewModel.deadline.value =
                binding!!.deadlineTextView.text.toString().stringToTimestamp()


            if (viewModel.UUID.value != null)
                viewModel.event(EditOrAddEvent.UpdateTodo)
            else
                viewModel.event(EditOrAddEvent.SaveTodo)
            findNavController().popBackStack()
        }


        val date = Timestamp.now().toDate()

        val calendar = Calendar.getInstance()
        calendar.time = date

        binding!!.deadlineLine.setOnClickListener {

            datePickerDialog(calendar) { view, year, month, dayOfMonth ->
                timePickerDialog(calendar, year, month, dayOfMonth)
            }

        }
    }

    private fun datePickerDialog(
        calendar: Calendar,
        click: (DatePicker, Int, Int, Int) -> Unit
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
    ) {
        val timePicker = TimePickerDialog(
            requireContext(),
            R.style.AppTimePickerDialogTheme,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)


                binding!!.deadlineTextView.text = calendar.time.dateToString()

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


    private fun Date.dateToString(): String {
        val timeFormat = SimpleDateFormat("yyyy MMM dd HH:mm", Locale.getDefault())

        return timeFormat.format(this)
    }


    private fun Timestamp.timestampToString(): String {
        val timeFormat = SimpleDateFormat("yyyy MMM dd HH:mm", Locale.getDefault())
        val date = Date(this.seconds * 1000 + this.nanoseconds / 1000000)

        return timeFormat.format(date)
    }

    private fun String.stringToTimestamp(): Timestamp {
        val timeFormat = SimpleDateFormat("yyyy MMM dd HH:mm", Locale.getDefault())
        val date = timeFormat.parse(this)

        return Timestamp(date!!)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}