package com.dev6.join

import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dev6.common.uistate.UiState
import com.dev6.core.base.BindingFragment
import com.dev6.domain.model.join.JoinReq
import com.dev6.enums.UserType
import com.dev6.join.databinding.FragmentJoinNickNameBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JoinNickNameFragment :
    BindingFragment<FragmentJoinNickNameBinding>(R.layout.fragment_join_nick_name) {
    private val joinViewModel: JoinViewModel by viewModels()
    lateinit var joinReq: JoinReq
    override fun initView() {
        super.initView()
        binding.include.tvLeft.visibility = View.GONE
        binding.include.tvTop.text = ""
        binding.include.tvRight.text = "건너뛰기 >"
    }

    override fun initViewModel() {
        super.initViewModel()
        //TODO 닉네임 수정 api 호출 결과값 핸들러 작성
    }

    override fun initListener() {
        super.initListener()

        binding.nameTextSub.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                editTextHandler()
            }
        })

        binding.authButton.setOnClickListener {
            //TODO 클릭시 닉네임 수정 api 호출
        }

        binding.include.tvLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()

    }


    private fun editTextHandler() {
        binding.apply {
            when (true) { // 중복체크?
                true -> {
                    authButton.isClickable = true
                    authButton.setBackgroundResource(com.dev6.designsystem.R.drawable.round_active)
                    authButton.setTextColor(
                        ContextCompat.getColor(
                            requireActivity(),
                            com.dev6.designsystem.R.color.white
                        )
                    )
                }
                false -> {
                    authButton.isClickable = false
                    authButton.setBackgroundResource(com.dev6.designsystem.R.drawable.round_non)
                    authButton.setTextColor(
                        ContextCompat.getColor(
                            requireActivity(),
                            com.dev6.designsystem.R.color.nonActiveButtonTextColor
                        )
                    )
                }
            }
        }
    }
}