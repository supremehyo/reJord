package com.dev6.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.dev6.core.R

//사용 보류 : style 을 domain 에서 관리할 수 없음
abstract class BindingDialog<T: ViewDataBinding>(
    @LayoutRes private val layoutId: Int) : DialogFragment() {
    protected lateinit var binding: T

    // 버튼이 한개인지 두개인지
    enum class ButtonType{
        ONE, TWO
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   //     setStyle(STYLE_NO_TITLE, R.style.my_dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setEvent()
    }

    open fun initView() {}
    open fun setEvent() {}

}