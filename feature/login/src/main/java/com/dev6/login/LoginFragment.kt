package com.dev6.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.login.databinding.FragmentLoginBinding


class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

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

        binding.goJoin.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_join_graph)
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }
}