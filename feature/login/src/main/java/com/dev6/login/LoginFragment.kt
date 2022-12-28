package com.dev6.login

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.core.util.Validation
import com.dev6.login.databinding.FragmentLoginBinding


class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    var userId = ""
    var passWord = ""
    private var errors = mutableListOf(true,true)
    var validation = Validation()
    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
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
            if(allDataComplete()){
                Toast.makeText(requireContext(), "로그인은 개발중임당~~", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "입력하지 않은 값이 있습니다.", Toast.LENGTH_SHORT).show()
            }
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


}