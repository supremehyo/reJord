package com.dev6.join

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.join.databinding.FragmentJoinNickNameBinding


class JoinNickNameFragment : BindingFragment<FragmentJoinNickNameBinding>(R.layout.fragment_join_nick_name) {

    override fun initView() {
        super.initView()
        binding.include.tvTop.text = ""
        binding.include.tvRight.text = ""
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
        binding.include.tvLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }
}