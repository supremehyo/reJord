package com.dev6.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.BindingFragment
import com.dev6.home.R
import com.dev6.home.adapter.BadgeAdapter
import com.dev6.home.databinding.FragmentMyBadgeBinding


class MyBadgeFragment : BindingFragment<FragmentMyBadgeBinding>(R.layout.fragment_my_badge) {
    lateinit var badgeRc : RecyclerView
    lateinit var badgeRcAdapter : BadgeAdapter
    override fun initView() {
        super.initView()
        badgeRc = binding.badgeRc
        badgeRcAdapter = BadgeAdapter{

        }
        badgeRcAdapter.submitList(listOf("뱃지","뱃지","뱃지","뱃지","뱃지",
            "뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지",
            "뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지",
            "뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지","뱃지",
            "뱃지","뱃지","뱃지","뱃지")
        )
        badgeRc.apply {
            adapter = badgeRcAdapter
            layoutManager = GridLayoutManager(requireContext(),3)
        }
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }
}