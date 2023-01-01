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
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dev6.common.uistate.UiState
import com.dev6.core.base.BindingFragment
import com.dev6.core.util.Validation
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.nickName.NicknameReq
import com.dev6.core.enums.UserType
import com.dev6.join.databinding.FragmentJoinNickNameBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JoinNickNameFragment :
    BindingFragment<FragmentJoinNickNameBinding>(R.layout.fragment_join_nick_name) {
    private val joinViewModel: JoinViewModel by viewModels()
    var validation = Validation()
    var nickname = ""
    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStartedFragment {
            joinViewModel.eventFlow.collect {
                handleEvent(it)
            }
        }
    }

    override fun initListener() {
        super.initListener()

        binding.nickNameSkipLl.setOnClickListener {
            //홈으로
        }

        binding.nameTextSub.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                nickname = binding.nameTextSub.text.toString()
                checkNicknameValidation(nickname)
            }
        })

        binding.authButton.setOnClickListener {

        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()

    }

    private fun handleEvent(event: JoinViewModel.Event) = when (event) {

        is JoinViewModel.Event.userDataUpdateUiEvent -> {
            when (event.uiState) {
                is UiState.Loding -> {
                    Log.v("join 회원정보 수정", "요청중")
                }
                is UiState.Success -> {
                    Log.v("join 회원정보 수정", "성공 홈으로 이동")
                    //홈으로 이동
                    //findNavController().navigate()
                }
                is UiState.Error -> {
                    Toast.makeText(requireContext(), event.toString(), Toast.LENGTH_SHORT).show()
                    Log.v("join 회원정보 수정", "실패")
                }
            }

        }
        else -> {}
    }

    private fun checkNicknameValidation(nickname: String) {
        if (validation.checkNickNamePattern(nickname)) {
            nickNameSuccess()
        } else {
            nickNameError()
        }
    }

    private fun nickNameError() {
        binding.nickNameStatusTv.setTextColor(
            ContextCompat.getColor(requireActivity(), com.dev6.designsystem.R.color.typoError)
        )
        editTextHandler(false)
    }

    private fun nickNameSuccess() {
        binding.nickNameStatusTv.setTextColor(
            ContextCompat.getColor(requireActivity(), com.dev6.designsystem.R.color.mainColor)
        )
        editTextHandler(true)
    }


    private fun editTextHandler(boolean: Boolean) {
        binding.apply {
            when (boolean) {
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