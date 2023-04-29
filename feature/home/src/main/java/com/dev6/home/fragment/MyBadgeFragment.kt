package com.dev6.home.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.home.R
import com.dev6.home.adapter.BadgeAdapter
import com.dev6.home.databinding.FragmentMyBadgeBinding
import com.dev6.home.viewmodel.MyPageViewModel


class MyBadgeFragment : BindingFragment<FragmentMyBadgeBinding>(R.layout.fragment_my_badge) {
    lateinit var badgeRc : RecyclerView
    lateinit var badgeRcAdapter : BadgeAdapter
    private val myPageViewModel: MyPageViewModel by activityViewModels()
    override fun initView() {
        super.initView()
        badgeRc = binding.badgeRc
        badgeRcAdapter = BadgeAdapter{}
        badgeRc.apply {
            adapter = badgeRcAdapter
            layoutManager = GridLayoutManager(requireContext(),3)
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            myPageViewModel.getBadgeInfoList()
        }
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            myPageViewModel.myPageFlow.collect{it->
                when (it) {
                    is MyPageViewModel.MyPageEvent.GetMyBadgeInfoList ->{
                        when(it.uiState){
                            is UiState.Success -> {
                                badgeRcAdapter.submitList(
                                    it.uiState.data
                                )
                            }
                            is UiState.Loding -> {

                            }
                            is UiState.Error -> {
                                Log.v("MyPage Error", it.toString())
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }
}