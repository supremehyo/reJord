package com.dev6.home.fragment
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.dev6.common.uistate.UiState
import com.dev6.core.base.BindingFragment
import com.dev6.domain.model.post.read.PostReadReq
import com.dev6.home.HomeViewModel
import com.dev6.home.adapter.HomeContentPagerAdapter
import com.dev6.home.R
import com.dev6.home.databinding.FragmentHomeMainBinding
import com.dev6.home.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class MainHomeFragment : BindingFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {
    val mainViewModel : MainViewModel by activityViewModels()

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
        mainViewModel.getPostList(
            PostReadReq(0, LocalDateTime.now().toString(),5)
        )
    }

    override fun initListener() {
        super.initListener()
        binding.upFab.setOnClickListener {

        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

}