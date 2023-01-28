package com.dev6.home.fragment
import com.dev6.core.base.BindingFragment
import com.dev6.home.HomeContentPagerAdapter
import com.dev6.home.R
import com.dev6.home.databinding.FragmentHomeMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHomeFragment : BindingFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {

    override fun initView() {
        super.initView()
        binding.pagerContent.adapter = HomeContentPagerAdapter(this@MainHomeFragment)
        binding.pagerContent.isSaveEnabled = false
        TabLayoutMediator(binding.tableLayout, binding.pagerContent) {
                tab, position ->
            if(position == 0){
                tab.text = "챌린지 후기"
            }else{
                tab.text = "게시판"
            }
        }.attach()
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