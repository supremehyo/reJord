package com.dev6.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev6.core.BindingFragment
import com.dev6.home.R
import com.dev6.home.bottomsheet.WithdrawalSheetFragment
import com.dev6.home.databinding.FragmentUserInfoBinding
import com.dev6.write.fragment.WriteBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class UserInfoFragment : BindingFragment<FragmentUserInfoBinding>(R.layout.fragment_user_info) {
    lateinit var bottomSheet : BottomSheetDialogFragment
    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
        binding.WithdrawalBt.setOnClickListener {
            bottomSheet =  WithdrawalSheetFragment()
            bottomSheet.show(parentFragmentManager , bottomSheet.tag)
        }
        //수정완료
        binding.userEditBt.setOnClickListener {

        }
        binding.closeIv.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }
}