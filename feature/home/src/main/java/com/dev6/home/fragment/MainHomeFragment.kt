package com.dev6.home.fragment

import android.util.Log
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.enums.ScrollType
import com.dev6.core.enums.WriteType
import com.dev6.domain.model.challenge.ChallengeInfoRes
import com.dev6.domain.model.post.read.PostReadReq
import com.dev6.home.adapter.HomeContentPagerAdapter
import com.dev6.home.R
import com.dev6.home.databinding.FragmentHomeMainBinding
import com.dev6.home.viewmodel.BoardViewModel
import com.dev6.home.viewmodel.ChallengeViewModel
import com.dev6.write.viewmodel.WriteViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class MainHomeFragment : BindingFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {
    private val boardViewModel: BoardViewModel by activityViewModels()
    private val challengeViewModel: ChallengeViewModel by activityViewModels()
    val writeViewModel : WriteViewModel by activityViewModels()

    lateinit var job: Job
    override fun initView() {
        super.initView()
        binding.pagerContent.adapter = HomeContentPagerAdapter(this@MainHomeFragment)
        binding.pagerContent.isSaveEnabled = false
        binding.tableLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        boardViewModel.checkBoardTabType(WriteType.CHALLENGE)
                    }
                    1 -> {
                        boardViewModel.checkBoardTabType(WriteType.SHARE)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        TabLayoutMediator(binding.tableLayout, binding.pagerContent) { tab, position ->
            if (position == 0) {
                tab.text = "챌린지 후기"
            } else {
                tab.text = "게시판"
            }
        }.attach()

        repeatOnStartedFragment {
            boardViewModel.getBannerData()
        }

    }

    override fun initViewModel() {
        super.initViewModel()
        boardViewModel.scrollFlag.observe(viewLifecycleOwner) {
            when (it) {
                ScrollType.TOP -> {

                    binding.upFab.alpha = 1.0f
                }
                ScrollType.SCROLLUP -> {
                    binding.upFab.alpha = 1.0f
                }
                ScrollType.SCROLLDONW -> {
                    binding.upFab.alpha = 0.2f
                }
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.upFab.apply {
            setOnClickListener {
                boardViewModel.upScroll()
                challengeViewModel.upScroll()
                binding.upFab.alpha = 1.0f
                binding.appbarLayout.setExpanded(true)
            }
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        job = lifecycleScope.launch {
            boardViewModel.BoardeventFlow.collect {
                eventHandler(it)
            }
        }
    }

    private fun eventHandler(event: BoardViewModel.BoardEvent) {
        when (event) {
            is BoardViewModel.BoardEvent.BannerEvent -> {
                when (event.uiState) {
                    is UiState.Success -> {
                        initBanner(event.uiState.data)
                        job.cancel()// 다른화면에서도 BoardeventFlow 를
                        //collect 하고 있어서 구독을 풀어줘야함
                    }
                    is UiState.Loding -> {

                    }
                    is UiState.Error -> {

                    }

                }
            }
            else -> {

            }
        }
    }



    private fun initBanner(challengeInfoRes: ChallengeInfoRes) {
        //챌린지 글쓰기할때 챌린지 id
        writeViewModel.changeChallengeIdData(
            challengeInfoRes.challengeId
        )
        binding.dataf.setBannerLayout(challengeInfoRes)
    }

    override fun onResume() {
        super.onResume()
    }
}