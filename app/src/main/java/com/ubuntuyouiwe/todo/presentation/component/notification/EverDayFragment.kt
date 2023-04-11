package com.ubuntuyouiwe.todo.presentation.component.notification

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentEverDayBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class EverDayFragment : Fragment() {
    private var binding: FragmentEverDayBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEverDayBinding.inflate(inflater,container,false)
        return binding!!.root




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.button.setOnClickListener {
            val date = Timestamp.now().toDate()
            val calendar = Calendar.getInstance()
            calendar.time = date

            val timePicker = TimePickerDialog(
                context,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                    val formattedTime = timeFormat.format(calendar.time)
                    binding!!.button.text = formattedTime

                },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true
            )

            timePicker.setTitle("Saat Seçiniz")
            timePicker.setButton(DialogInterface.BUTTON_POSITIVE,"Ayarlar",timePicker)
            timePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"iptal",timePicker)
            timePicker.show()

        }

    }


}