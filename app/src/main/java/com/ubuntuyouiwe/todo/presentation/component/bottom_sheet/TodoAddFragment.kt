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
import androidx.core.graphics.alpha
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.ubuntuyouiwe.todo.R
import com.ubuntuyouiwe.todo.databinding.FragmentItemListDialogListDialogBinding
import kotlinx.coroutines.launch

class TodoAddFragment : BottomSheetDialogFragment() {

    var binding: FragmentItemListDialogListDialogBinding? = null
        private set

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentItemListDialogListDialogBinding.inflate(inflater, container, false)

        val backgroundShape = MaterialShapeDrawable(
            ShapeAppearanceModel.builder()
                .setAllCornerSizes(16f * resources.displayMetrics.density)
                .build()
        )
        backgroundShape.fillColor = binding?.root?.backgroundTintList


        val backgroundColor = resources.getColor(R.color.color_background, context?.theme)


        backgroundShape.setTint(backgroundColor)


        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#80000000")))


        dialog?.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)


            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.isHideable = true



            /*behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                private var lastSlideOffset = 0f

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING && lastSlideOffset > 0.5) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    lastSlideOffset = slideOffset
                }
            })
*/
            val contentHeight = binding!!.scrollView.getChildAt(0).height

            val scrollViewHeight = binding!!.scrollView.height

            if (contentHeight > scrollViewHeight) {
                binding!!.scrollView.setOnTouchListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            behavior.isDraggable = false

                        }

                        MotionEvent.ACTION_UP -> {
                            behavior.isDraggable = true

                        }
                    }

                    false
                }

            } else {
                behavior.isDraggable = true

            }


            bottomSheet.background = backgroundShape
        }


        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.navigatonNavhost) as NavHostFragment
        val navController = navHostFragment.navController

        binding!!.everyDay.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                navController.navigate(R.id.everDayFragment)

            }
            Log.v("asdsdadsa",navController.toString())

        }
        binding!!.everyWeek.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                navController.navigate(R.id.everyWeekFragment)
        }
        binding!!.everyYear.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                navController.navigate(R.id.everyYearFragment)
        }
        binding!!.monthly.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                navController.navigate(R.id.monthlyFragment)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}