package com.dev6.home.fragment
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import com.dev6.core.BindingFragment
import com.dev6.core.enums.ScrollType
import com.dev6.core.enums.WriteType
import com.dev6.home.adapter.HomeContentPagerAdapter
import com.dev6.home.R
import com.dev6.home.databinding.FragmentHomeMainBinding
import com.dev6.home.viewmodel.BoardViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHomeFragment : BindingFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {
    val boardViewModel : BoardViewModel by activityViewModels()
    override fun initView() {
        super.initView()
        binding.pagerContent.adapter = HomeContentPagerAdapter(this@MainHomeFragment)
        binding.pagerContent.isSaveEnabled = false
        binding.pagerContent
        binding.tableLayout.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    //TODO enum 으로 관리
                    0->{ boardViewModel.checkBoardTabType(WriteType.CHALLENGE) }
                    1->{ boardViewModel.checkBoardTabType(WriteType.SHARE) }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        TabLayoutMediator(binding.tableLayout, binding.pagerContent) {
                tab, position ->
            if(position == 0){
                tab.text = "챌린지 후기"

            }
            else{ tab.text = "게시판" }
        }.attach()
    }

    override fun initViewModel() {
        super.initViewModel()
        boardViewModel.scrollFlag.observe(viewLifecycleOwner){
            when(it){
                ScrollType.TOP ->{
                    Log.v("sdfsdfs" , "탑탑")
                    binding.upFab.alpha = 1.0f
                }
                ScrollType.SCROLLUP ->{
                    binding.upFab.alpha = 1.0f
                }
                ScrollType.SCROLLDONW ->{
                    binding.upFab.alpha = 0.2f
                }
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.upFab.apply {
            setOnClickListener{
                boardViewModel.upScroll()
                binding.upFab.alpha = 1.0f
                binding.appbarLayout.setExpanded(true)
            }
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    private fun initBanner(){
       // binding.data
    }

}