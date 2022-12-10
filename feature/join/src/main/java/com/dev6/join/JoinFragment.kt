package com.dev6.join

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dev6.common.uistate.UiState
import com.dev6.core.base.BindingFragment
import com.dev6.domain.model.join.JoinReq
import com.dev6.join.databinding.FragmentJoinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinFragment : BindingFragment<FragmentJoinBinding>(R.layout.fragment_join) {
    private val joinViewModel: JoinViewModel by viewModels()
    private var terms = mutableListOf(false,false,false)


    override fun initView() {
        super.initView()
        binding.include.tvTop.text = ""
        binding.include.tvRight.text = ""

        binding.customEditTextEmailSub.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                editTextHandler(p0.toString().isNotEmpty())
            }
        })
    }

    override fun initViewModel() {
        super.initViewModel()
        var join = JoinReq("이름")
        joinViewModel.userJoin(join)

        repeatOnStartedFragment {
            joinViewModel.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    override fun initListener() {
        super.initListener()
        checkTerms()
        AuthButton()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.JoinEvent -> {
            when (event.uiState) {
                is UiState.Loding -> {

                }
                is UiState.Success -> {

                }
                is UiState.Error -> {

                }
            }
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
            if(ActiveAuthButton(terms)){
                findNavController().navigate(R.id.action_JoinFragment_to_JoinNickNameFragemnt)
            }else{
                Toast.makeText(requireContext(), "약관에 동의해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun editTextHandler(boolean: Boolean) {
        when (boolean) {
            true -> {
               // binding.customEditTextEmailSub.setCompoundDrawablesWithIntrinsicBounds(0, 0, com.dev6.designsystem.R.drawable.check_black, 0)
                binding.authButton.setBackgroundResource(com.dev6.designsystem.R.drawable.round_active)
                binding.authButton.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.white))
            }
            false -> {
                //binding.customEditTextEmailSub.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                binding.authButton.setBackgroundResource(com.dev6.designsystem.R.drawable.round_non)
                binding.authButton.setTextColor(getColor(requireActivity(), com.dev6.designsystem.R.color.nonActiveButtonTextColor))
            }
        }
    }
}