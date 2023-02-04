package com.dev6.core
import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<B : ViewDataBinding>(private val layoutId: Int) :
    BottomSheetDialogFragment() {
    lateinit var binding: B
    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = requireActivity()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initView()
        initListener()
        return binding.root
    }

    abstract fun initView()

    abstract fun initListener()

    open fun setCancel(setting: Boolean) {
        isCancelable = setting
    }

    open fun setWindowFeature(feature: Int) {
        activity.requestWindowFeature(feature)
    }

    open fun setBackground(drawable: ColorDrawable) {
        activity.window?.setBackgroundDrawable(drawable)
    }
}