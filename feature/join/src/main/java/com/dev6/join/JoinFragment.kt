package com.dev6.join

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dev6.common.uistate.UiState
import com.dev6.core.base.BindingFragment
import com.dev6.core.util.Validation
import com.dev6.domain.model.join.JoinReq
import com.dev6.join.databinding.FragmentJoinBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern


@AndroidEntryPoint
class JoinFragment : BindingFragment<FragmentJoinBinding>(R.layout.fragment_join) {
    private val joinViewModel: JoinViewModel by activityViewModels()
    private var errors = mutableListOf(false,false,false) // 해당 error 처리도 다 viewmodel 로 추후에 뺄 예정
    var userId = ""
    var passWord = ""
    var userType = "ADMIN"
    var nickNameCheck = false
    var validation = Validation()


    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStartedFragment {
            joinViewModel.eventFlow.collect { event -> handleEvent(event) }
        }

    }

    override fun initListener() {
        super.initListener()
        AuthButton()

        binding.textView5.setOnClickListener {
            if(errors[0]){
                joinViewModel.userUserIdExistCheck(userId)
            }
        }

        binding.backHomeLl.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.customEditTextEmailSub.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(edit: Editable?) {
                emailValidation(binding.customEditTextEmailSub.text.toString())
                editTextHandler()
            }
        })

        binding.customEditTextPasswordSub1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                joinPassWordValidation(binding.customEditTextPasswordSub1.text.toString())
                joinPassWordConfirmValidation()
                editTextHandler()
            }
        })

        binding.customEditTextPasswordSub2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                joinPassWordConfirmValidation()
                editTextHandler()
            }
        })
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    private fun joinPassWordValidation(pw : String){
        if(validation.checkPwPattern(pw)){
            passWord = binding.customEditTextPasswordSub1.text.toString()
            // TODO 아래 error text 처리도 모듈화 처리 할 것
            binding.passwordErrorText.visibility =View.VISIBLE
            binding.passwordErrorText.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.mainColor))
            binding.passwordErrorText.text = "사용가능한 비밀번호 입니다."
            errors[1] = true
        }else{
            binding.passwordErrorText.visibility =View.VISIBLE
            binding.passwordErrorText.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.typoError))
            binding.passwordErrorText.text = "영문과 숫자를 포함하여 최소 5글자 이상을 입력해주세요."
            errors[1] = false
        }
    }

    private fun emailValidation(id : String){
        if(validation.checkIdPattern(id)){
            userId = binding.customEditTextEmailSub.text.toString()
            binding.emailErrorText.visibility = View.GONE
            errors[0] = true
        } else {
            binding.emailErrorText.visibility = View.VISIBLE
            binding.emailErrorText.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.typoError))
            binding.emailErrorText.text = "영문과 숫자를 포함하여 최소 5글자 이상을 입력해주세요."
            errors[0] = false
        }
    }

    private fun joinPassWordConfirmValidation(){
        if(binding.customEditTextPasswordSub1.text.toString() != binding.customEditTextPasswordSub2.text.toString()){
            binding.passwordErrorConfirmText.visibility = View.VISIBLE
            binding.passwordErrorConfirmText.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.typoError))
            binding.passwordErrorConfirmText.text = "비밀번호가 일치하지 않습니다."
            errors[2] = false
        }else if(binding.customEditTextPasswordSub1.text.toString() == binding.customEditTextPasswordSub2.text.toString()){
            passWord = binding.customEditTextPasswordSub1.text.toString()
            binding.passwordErrorConfirmText.visibility = View.VISIBLE
            binding.passwordErrorConfirmText.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.mainColor))
            binding.passwordErrorConfirmText.text = "사용가능한 비밀번호 입니다."
            errors[2] = true
        }
    }

    private fun ActiveAuthButton(mutableList2: MutableList<Boolean>) : Boolean{
        return  mutableList2.all { it }
    }

    private fun AuthButton(){
        binding.authButton.setOnClickListener {
            //회원가입 요청
            joinViewModel.userJoin(JoinReq(password = passWord , userId = userId, userType))
        }
    }

    private fun allDataComplete() : Boolean{
        return (ActiveAuthButton(errors)) && nickNameCheck
    }

    private fun editTextHandler() {
        when (allDataComplete()) {
            true -> {
                binding.authButton.setBackgroundResource(com.dev6.designsystem.R.drawable.round_active)
                binding.authButton.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.white))
                binding.authButton.isClickable = true
            }
            false -> {
                binding.authButton.setBackgroundResource(com.dev6.designsystem.R.drawable.round_non)
                binding.authButton.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.nonActiveButtonTextColor))
                binding.authButton.isClickable = false
            }
        }
    }


    private fun handleEvent(event: JoinViewModel.Event) = when (event) {
        is JoinViewModel.Event.UiEvent -> {
            when (event.uiState) {
                is UiState.Loding -> {
                    Log.v("join api 상태", "요청중")
                }
                is UiState.Success -> {
                    Log.v("join api 상태", "성공")
                    JoinDialogFragment(){
                        val bundle = bundleOf("JoinReq" to JoinReq( password = passWord , userId = userId, userType))
                        findNavController().navigate(R.id.action_JoinFragment_to_JoinNickNameFragemnt , bundle)
                    }.show(parentFragmentManager,"")
                }
                is UiState.Error -> {
                    Toast.makeText(requireContext(), event.toString(), Toast.LENGTH_SHORT).show()
                    Log.v("join api 상태", "실패")
                }
            }
        }
        is JoinViewModel.Event.userUserIdExistUiEvent -> {
            when (event.uiState) {
                is UiState.Loding -> {
                    Log.v("join id 체크", "요청중")
                }
                is UiState.Success -> {
                    Log.v("join id 체크", "성공")
                    nickNameCheck = true
                    binding.emailErrorText.visibility = View.VISIBLE
                    binding.emailErrorText.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.mainColor))
                    binding.emailErrorText.text = "사용가능한 아이디입니다."
                }
                is UiState.Error -> {
                    Log.v("join id 체크", "실패")
                    binding.emailErrorText.visibility = View.VISIBLE
                    binding.emailErrorText.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.typoError))
                    binding.emailErrorText.text = "이미 가입된 아이디입니다."
                }
            }
        }
        else -> {}
    }
}