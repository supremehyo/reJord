package com.dev6.join

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
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
    private var terms = mutableListOf(true,true,true) // 기존엔 모두 false 약관체크 빠지게 되면 다시 false 돌림
    private var errors = mutableListOf(true,true,true)
    val emailPattern: Pattern = Patterns.EMAIL_ADDRESS
    var userId = "test.com"
    var passWord = "12345678"
    var userType = "ADMIN"
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
        checkTerms()
        AuthButton()

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
            binding.passwordErrorText.visibility =View.VISIBLE
            errors[1] = false
        }else{
            binding.passwordErrorText.visibility =View.GONE
            errors[1] = true
        }
    }

    private fun emailValidation(id : String){
        if(validation.checkIdPattern(id)){
            userId = binding.customEditTextEmailSub.text.toString()
            binding.emailErrorText.visibility = View.GONE
            errors[0] = true
        } else {
            binding.emailErrorText.visibility = View.VISIBLE
            errors[0] = false
        }
    }

    private fun joinPassWordConfirmValidation(){
        if(binding.customEditTextPasswordSub1.text.toString() != binding.customEditTextPasswordSub2.text.toString()){
            binding.passwordErrorConfirmText.visibility = View.VISIBLE
            errors[2] = false
        }else if(binding.customEditTextPasswordSub1.text.toString() == binding.customEditTextPasswordSub2.text.toString()){
            passWord = binding.customEditTextPasswordSub1.text.toString()
            binding.passwordErrorConfirmText.visibility = View.GONE
            errors[2] = true
        }
    }


    private fun checkTerms(){
        //약관 체크 박스
        /*
        binding.checkBox1.setOnCheckedChangeListener { compoundButton, isChecked -> terms[0] = isChecked }
        binding.checkBox2.setOnCheckedChangeListener { compoundButton, isChecked -> terms[1] = isChecked }
        binding.checkBox3.setOnCheckedChangeListener { compoundButton, isChecked -> terms[2] = isChecked }
         */
    }

    private fun ActiveAuthButton(mutableList: MutableList<Boolean> , mutableList2: MutableList<Boolean>) : Boolean{
        return mutableList.all { it } && mutableList2.all { it }
    }

    private fun AuthButton(){
        binding.authButton.setOnClickListener {
            //회원가입 요청
            //joinViewModel.userJoin(JoinReq(password = passWord , userId = userId, userType))

            //팝업 test
            JoinDialogFragment(){
                findNavController().navigate(R.id.action_JoinFragment_to_JoinNickNameFragemnt)
            }.show(parentFragmentManager,"")
        }
    }

    private fun allDataComplete() : Boolean{
        return (ActiveAuthButton(terms,errors)
                && binding.emailErrorText.visibility == View.GONE
                && binding.passwordErrorText.visibility == View.GONE
                && binding.passwordErrorConfirmText.visibility == View.GONE)
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
                    val bundle = bundleOf("JoinReq" to JoinReq( password = passWord , userId = userId, userType))
                    findNavController().navigate(R.id.action_JoinFragment_to_JoinNickNameFragemnt , bundle)
                }
                is UiState.Error -> {
                    Toast.makeText(requireContext(), event.toString(), Toast.LENGTH_SHORT).show()
                    Log.v("join api 상태", "실패")
                }
            }
        }
    }
}