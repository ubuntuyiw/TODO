package com.ubuntuyouiwe.todo.presentation.component.bottom_sheet

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.graphics.alpha
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentItemListDialogListDialogBinding
import com.ubuntuyouiwe.todo.presentation.component.notification.NotificationSharedViewModel
import kotlinx.coroutines.launch

class TodoAddFragment : BottomSheetDialogFragment() {

    var binding: FragmentItemListDialogListDialogBinding? = null
        private set
    private val notificationState = ArrayList<String>()
    private lateinit var notificationStateAdapter: ArrayAdapter<String>
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentItemListDialogListDialogBinding.inflate(inflater, container, false)

        val backgroundShape = MaterialShapeDrawable(
            ShapeAppearanceModel.builder()
                .setTopLeftCornerSize(16f * resources.displayMetrics.density)
                .setTopRightCornerSize(16f * resources.displayMetrics.density)
                .build()
        )
        backgroundShape.fillColor = binding?.root?.backgroundTintList

        val backgroundColor = resources.getColor(R.color.white, context?.theme)

        backgroundShape.setTint(backgroundColor)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#80000000")))

        dialog?.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.isHideable = true
            behavior.isDraggable = false
            behavior.peekHeight = resources.displayMetrics.heightPixels



           /* binding!!.titleArea.setOnTouchListener { _, event ->
                behaviorDraggable(event.action, behavior)
                false
            }

            binding!!.titleEditText.setOnTouchListener { _, event ->
                behaviorDraggable(event.action, behavior)
                false
            }

            binding!!.bottomSheetLine.setOnTouchListener { _, event ->
                behaviorDraggable(event.action, behavior)
                false
            }
            binding!!.saveTodo.setOnTouchListener { _, event ->
                behaviorDraggable(event.action, behavior)
                false
            }*/


            bottomSheet.background = backgroundShape
        }


        return binding!!.root
    }

    private fun behaviorDraggable(
        eventAction: Int,
        behaviorIsDraggable: BottomSheetBehavior<View>
    ) {
        when (eventAction) {
            MotionEvent.ACTION_DOWN -> {
                behaviorIsDraggable.isDraggable = true
                Log.v("dedededede","true")

            }
            MotionEvent.ACTION_UP -> {
                behaviorIsDraggable.isDraggable = false
                Log.v("dedededede","false")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.navigatonNavhost) as NavHostFragment
        val navController = navHostFragment.navController

        val notificationSharedViewModel: NotificationSharedViewModel by activityViewModels()



        notificationState.add("Every Day")
        notificationState.add("Every Week")
        notificationState.add("Monthly")
        notificationState.add("Every Year")

        notificationStateAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_2,android.R.id.text2,notificationState)
        binding!!.notificationState.adapter = notificationStateAdapter

        binding!!.notificationState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0)
                    navController.navigate(R.id.everDayFragment)
                else if(position == 1)
                    navController.navigate(R.id.everyWeekFragment)
                else if(position == 2)
                    navController.navigate(R.id.everyYearFragment)
                else if(position == 3)
                    navController.navigate(R.id.monthlyFragment)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                navController.navigate(R.id.everDayFragment)
            }

        }
        binding!!.saveTodo.setOnClickListener {
            val state = notificationSharedViewModel.notificationState
            val timestamp = notificationSharedViewModel.timestamp
            Log.v("selamasd","${state.value} ${timestamp.value}")

        }








    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}