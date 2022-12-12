package com.dev6.join

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.domain.model.join.JoinReq
import com.dev6.join.databinding.FragmentJoinBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class JoinFragment : BindingFragment<FragmentJoinBinding>(R.layout.fragment_join) {
   // private val joinViewModel: JoinViewModel by activityViewModels()
    private var terms = mutableListOf(false,false,false)
    val emailPattern: Pattern = Patterns.EMAIL_ADDRESS
    var userId = "test2@test.com"
    var passWord = "12345678"
    var userType = "ADMIN"

    override fun initView() {
        super.initView()

      //  val bundle = bundleOf("JoinReq" to JoinReq(nickname = "",password = passWord , userId = userId, userType))
      //  findNavController().navigate(R.id.action_JoinFragment_to_JoinNickNameFragemnt , bundle)

        binding.include.tvTop.text = ""
        binding.include.tvRight.text = ""
    }

    override fun initViewModel() {
        super.initViewModel()

    }

    override fun initListener() {
        super.initListener()
        checkTerms()
        AuthButton()

        binding.include.tvLeft.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.customEditTextEmailSub.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(edit: Editable?) {
                if(emailPattern.matcher(binding.customEditTextEmailSub.text.toString()).matches()){
                    userId = binding.customEditTextEmailSub.text.toString()
                    binding.customEditTextEmail.error = null
                } else {
                    binding.customEditTextEmail.isErrorEnabled = true
                    binding.customEditTextEmail.error = "이메일 형식이 아닙니다."
                }
                editTextHandler()
            }
        })

        binding.customEditTextPasswordSub1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                joinPassWordValidation()
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

    private fun joinPassWordValidation(){
        if(binding.customEditTextPasswordSub1.text!!.length < 8){
            binding.customEditTextPassword1.isErrorEnabled = true
            binding.customEditTextPassword1.error = "비밀번호는 최소 8자리로 구성해주세요."
        }else{
            binding.customEditTextPassword1.error = null
        }
    }

    private fun joinPassWordConfirmValidation(){
        if(binding.customEditTextPasswordSub1.text.toString() != binding.customEditTextPasswordSub2.text.toString()){
            binding.customEditTextPassword2.isErrorEnabled = true
            binding.customEditTextPassword2.error = "비밀번호가 일치하지 않습니다."
        }else if(binding.customEditTextPasswordSub1.text.toString() == binding.customEditTextPasswordSub2.text.toString()){
            passWord = binding.customEditTextPasswordSub1.text.toString()
            binding.customEditTextPassword2.error = null
        }
    }


    private fun checkTerms(){
        binding.checkBox1.setOnCheckedChangeListener { compoundButton, isChecked -> terms[0] = isChecked }
        binding.checkBox2.setOnCheckedChangeListener { compoundButton, isChecked -> terms[1] = isChecked }
        binding.checkBox3.setOnCheckedChangeListener { compoundButton, isChecked -> terms[2] = isChecked }
    }

    private fun ActiveAuthButton(mutableList: MutableList<Boolean>) : Boolean{
        return mutableList.all { it }
    }

    private fun AuthButton(){
        binding.authButton.setOnClickListener {
            if(allDataComplete()){
                val bundle = bundleOf("JoinReq" to JoinReq(nickname = "", password = passWord , userId = userId, userType))
                findNavController().navigate(R.id.action_JoinFragment_to_JoinNickNameFragemnt , bundle)
            }else if(ActiveAuthButton(terms)
                && binding.customEditTextEmail.error != null
                && binding.customEditTextPassword1.error != null
                && binding.customEditTextPassword2.error != null){
                Toast.makeText(requireContext(), "잘못된 데이터가 입력됐습니다.", Toast.LENGTH_SHORT).show()
            }else if(!ActiveAuthButton(terms)
                && binding.customEditTextEmail.error == null
                && binding.customEditTextPassword1.error == null
                && binding.customEditTextPassword2.error == null){
                Toast.makeText(requireContext(), "약관에 동의해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun allDataComplete() : Boolean{
        return (ActiveAuthButton(terms)
                && binding.customEditTextEmail.error == null
                && binding.customEditTextPassword1.error == null
                && binding.customEditTextPassword2.error == null)
    }

    private fun editTextHandler() {
        when (allDataComplete()) {
            true -> {
                binding.authButton.setBackgroundResource(com.dev6.designsystem.R.drawable.round_active)
                binding.authButton.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.white))
            }
            false -> {
                binding.authButton.setBackgroundResource(com.dev6.designsystem.R.drawable.round_non)
                binding.authButton.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.nonActiveButtonTextColor))
            }
        }
    }
}