package com.dev6.join

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.util.DevicePrefs
import com.dev6.core.util.Validation
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.domain.model.join.login.LoginRes
import com.dev6.domain.model.join.nickName.NicknameReq
import com.dev6.join.databinding.FragmentJoinNickNameBinding
import com.dev6.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JoinNickNameFragment :
    BindingFragment<FragmentJoinNickNameBinding>(R.layout.fragment_join_nick_name) {
    private val joinViewModel: JoinViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()
    var validation = Validation()
    var nickname = ""
    var userUid = ""

    var password = ""
    var userId = ""

    override fun initView() {
        super.initView()

        userUid = arguments?.getString("userUid").toString()
        password = arguments?.getString("password").toString()
        userId = arguments?.getString("userId").toString()
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStartedFragment {
            joinViewModel.eventFlow.collect {
                handleEvent(it)
            }
        }

        repeatOnStartedFragment {
            loginViewModel.eventFlow.collect { event -> handleLoginEvent(event) }
        }
    }

    override fun initListener() {
        super.initListener()

        binding.nickNameSkipLl.setOnClickListener {
            repeatOnStartedFragment {
                launch {
                    loginViewModel.userLogin(LoginReq(password, userId))
                }.join()
                findNavController().navigate(R.id.action_JoinNickNameFragemnt_to_home_graph)
            }
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
            joinViewModel.userDataUpdate(NicknameReq(nickname), userUid)
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
                    repeatOnStartedFragment {
                        loginViewModel.userLogin(LoginReq(password, userId))
                    }
                }
                is UiState.Error -> {
                    nickNameAlreadyError()
                    Log.v("join 회원정보 수정", event.uiState.error.toString())
                }
            }
        }
        else -> {}
    }

    private fun handleLoginEvent(loginEvent: LoginViewModel.Event) = when (loginEvent) {
        is LoginViewModel.Event.UiEvent -> {
            when (loginEvent.uiState) {
                is UiState.Loding -> {}
                is UiState.Success -> {
                    Log.v("Login 성공", "성공 홈으로 이동")
                    var tokens = (loginEvent.uiState as UiState.Success<LoginRes>).data.tokens
                    DevicePrefs.getInstance(requireContext()).saveToken(tokens.accessToken,tokens.refreshToken)
                    findNavController().navigate(R.id.action_JoinNickNameFragemnt_to_home_graph)
                }
                is UiState.Error -> {
                    Log.v("LoginError", loginEvent.toString())
                }
            }
        }
        else -> {}
    }


    private fun checkNicknameValidation(nickname: String) {
        if (validation.checkNickNamePattern(nickname)) {
            nickNameFormSuccess()
        } else if(!validation.checkNickNamePattern(nickname) || nickname.isEmpty()) {
            nickNameNotFormError()
        }
    }

    private fun nickNameNotFormError() {
        binding.nickNameStatusTv.setTextColor(
            ContextCompat.getColor(requireActivity(), com.dev6.designsystem.R.color.typoError)
        )
        binding.nickNameStatusTv.text ="형식에 맞지 않은 닉네임입니다."
        editTextHandler(false)
    }

    private fun nickNameAlreadyError() {
        binding.nickNameStatusTv.setTextColor(
            ContextCompat.getColor(requireActivity(), com.dev6.designsystem.R.color.typoError)
        )
        binding.nickNameStatusTv.text ="이미 사용중인 닉네임입니다."
        editTextHandler(false)
    }

    private fun nickNameFormSuccess() {
        binding.nickNameStatusTv.setTextColor(
            ContextCompat.getColor(requireActivity(), com.dev6.designsystem.R.color.mainColor)
        )
        binding.nickNameStatusTv.text ="사용가능한 닉네임입니다 :)"
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