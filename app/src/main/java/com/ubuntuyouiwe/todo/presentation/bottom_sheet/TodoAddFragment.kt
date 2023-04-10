package com.ubuntuyouiwe.todo.presentation.bottom_sheet

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
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
    ): View? {

        binding = FragmentItemListDialogListDialogBinding.inflate(inflater, container, false)

        val backgroundShape = MaterialShapeDrawable(
            ShapeAppearanceModel.builder()
                .setAllCornerSizes(32f)
                .build()
        )
        backgroundShape.fillColor = binding?.root?.backgroundTintList


        val backgroundColor = resources.getColor(R.color.color_background, context?.theme)
        backgroundShape.setTint(backgroundColor)




        dialog?.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

            val behavior = BottomSheetBehavior.from(bottomSheet!!)


            /*binding!!.constraintLayout2.setOnTouchListener { _, event ->
                behavior.isDraggable = true
                false
            }

            binding!!.scrollView.setOnTouchListener { _, event ->
                behavior.isDraggable = false
                false
            }*/

            behavior.peekHeight = 500
            behavior.isHideable = true

            bottomSheet?.background = backgroundShape
        }





        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}