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
        binding.include.tvTop.text = ""
        binding.include.tvRight.text = ""
    }

    override fun initViewModel() {
        super.initViewModel()
        joinReq = arguments?.getSerializable("JoinReq") as JoinReq
        repeatOnStartedFragment {
            joinViewModel.eventFlow.collect { event -> handleEvent(event) }
        }
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
            joinViewModel.userJoin(joinReq = joinReq.copy(nickname = binding.nameTextSub.text.toString()))
        }

        binding.include.tvLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    private fun handleEvent(event: JoinViewModel.Event) = when (event) {
        is JoinViewModel.Event.UiEvent -> {
            when (event.uiState) {
                is UiState.Loding -> {
                    Log.v("join api 상태", "요청중")
                }
                is UiState.Success -> {
                    Log.v("join api 상태", "성공")
                }
                is UiState.Error -> {
                    Toast.makeText(requireContext(), event.toString(), Toast.LENGTH_SHORT).show()
                    Log.v("join api 상태", "실패")
                }
            }
        }
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