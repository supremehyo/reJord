package com.dev6.login

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dev6.common.uistate.UiState
import com.dev6.core.base.BindingFragment
import com.dev6.core.util.Validation
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.home.HomeActivity
import com.dev6.join.JoinDialogFragment
import com.dev6.join.JoinViewModel
import com.dev6.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val loginViewModel: LoginViewModel by activityViewModels()
    var userId = ""
    var passWord = ""
    private var errors = mutableListOf(true,true)
    var validation = Validation()


    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStartedFragment {
            loginViewModel.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    override fun initListener() {
        super.initListener()
        goJoin()

        binding.customEditTextIdSub.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(edit: Editable?) {
                emailValidation(binding.customEditTextIdSub.text.toString())
            }
        })

        binding.customEditTextPasswordSub1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                passWordValidation(binding.customEditTextPasswordSub1.text.toString())
            }
        })

        binding.authButton.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_home_graph)

            /*
            if(allDataComplete()){
                loginViewModel.userLogin(LoginReq(passWord,userId))
            }else{
                Toast.makeText(requireContext(), "형식에 맞지 않은 값이 있습니다.", Toast.LENGTH_SHORT).show()
            }

             */
        }

    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    private fun goJoin(){
        binding.goJoin.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_join_graph)
        }
    }


    private fun emailValidation(id : String){
        if(validation.checkIdPattern(id)){
            userId = id
            binding.emailErrorText.visibility = View.GONE
            errors[0] = true
        } else {
            binding.emailErrorText.visibility = View.VISIBLE
            errors[0] = false
        }
    }

    private fun passWordValidation(pw : String){
        if(validation.checkPwPattern(pw)){
            passWord = pw
            binding.passwordErrorText.visibility = View.GONE
            errors[1] = true
        } else {
            binding.passwordErrorText.text = "영문과 숫자를 포함하여 최소 8글자 이상을 입력해주세요."
            binding.passwordErrorText.visibility = View.VISIBLE
            errors[1] = false
        }
    }


    private fun ActiveAuthButton(mutableList: MutableList<Boolean>) : Boolean{
        return mutableList.all { it }
    }


    private fun allDataComplete() : Boolean{
        return ActiveAuthButton(errors) && binding.passwordErrorText.visibility == View.GONE
    }

    private fun handleEvent(event: LoginViewModel.Event) = when(event){
        is LoginViewModel.Event.UiEvent ->{
            when(event.uiState){
                is UiState.Loding -> {

                }
                is UiState.Success -> {
                    Toast.makeText(requireContext(), event.toString(), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_LoginFragment_to_home_graph)
                }
                is UiState.Error -> {
                    Toast.makeText(requireContext(), event.toString(), Toast.LENGTH_SHORT).show()
                    binding.passwordErrorText.text = "아이디 또는 비밀번호가 일치하지 않습니다."
                    binding.passwordErrorText.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), event.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}