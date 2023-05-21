package com.dev6.home.bottomsheet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import com.dev6.core.BaseBottomSheetDialogFragment
import com.dev6.core.enums.WriteType
import com.dev6.home.databinding.FragmentOptionBottomSheetBinding
import com.dev6.write.R
import com.dev6.write.viewmodel.WriteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class OptionBottomSheetFragment : BaseBottomSheetDialogFragment<FragmentOptionBottomSheetBinding>(
    com.dev6.home.R.layout.fragment_option_bottom_sheet) {
    val writeFragment : WriteViewModel by activityViewModels()

    override fun initView() {

    }

    override fun initListener() {

    }
}