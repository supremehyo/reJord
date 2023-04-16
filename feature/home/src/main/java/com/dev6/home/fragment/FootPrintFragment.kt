package com.dev6.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.BindingFragment
import com.dev6.home.R
import com.dev6.home.adapter.FootPrintAdapter
import com.dev6.home.databinding.FragmentFootPrintBinding


class FootPrintFragment : BindingFragment<FragmentFootPrintBinding>(R.layout.fragment_foot_print) {
    lateinit var footprintRc : RecyclerView
    lateinit var footprintRcAdapter: FootPrintAdapter
    override fun initView() {
        super.initView()
        footprintRc = binding.footprintRc
        footprintRcAdapter = FootPrintAdapter { }
        footprintRcAdapter.submitList(listOf("테스트","테스트","테스트","테스트","테스트","테스트"))
        footprintRc.apply {
            adapter = footprintRcAdapter
            layoutManager = LinearLayoutManager(requireContext())
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