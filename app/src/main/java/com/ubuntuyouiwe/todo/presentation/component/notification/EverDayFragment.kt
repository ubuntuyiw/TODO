package com.ubuntuyouiwe.todo.presentation.component.notification

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.data.dto.remote.NotificationOption
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
        val viewModel: NotificationSharedViewModel by activityViewModels()


        viewModel.notificationState.value = NotificationOption.EVERY_DAY

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        val date = Timestamp.now().toDate()
        val calendar = Calendar.getInstance()
        calendar.time = date

        binding!!.button.text = timeFormat.format(calendar.time)

        binding!!.button.setOnClickListener {




            val timePicker = TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)


                    val formattedTime = timeFormat.format(calendar.time)
                    binding!!.button.text = formattedTime

                    val selectedDate = calendar.time
                    val firebaseTimestamp = Timestamp(selectedDate)


                    viewModel.timestamp.value = firebaseTimestamp


                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true
            )

            timePicker.setTitle("Saat Seçiniz")
            timePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Ayarlar", timePicker)
            timePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "iptal", timePicker)
            timePicker.show()



        }
        binding!!.addButton.setOnClickListener {
            viewButton(timeFormat.format(calendar.time))
        }








    }
    fun viewButton(text: String){

        val button = Button(requireContext())
        button.text = text
        binding!!.linearlayout.addView(button)

    }




}