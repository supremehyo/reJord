package com.dev6.home.fragment
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.mypage.MyData
import com.dev6.home.R
import com.dev6.home.adapter.MyPageContentPagerAdapter
import com.dev6.home.databinding.FragmentMyPageBinding
import com.dev6.home.viewmodel.MyPageViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment() : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val myPageViewModel: MyPageViewModel by activityViewModels()

    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            myPageViewModel.getMyData()
        }
    }

    override fun initListener() {
        super.initListener()
        myInfoEdit()
        myBadgeData()

        binding.mypageContent.adapter = MyPageContentPagerAdapter(this)
        binding.mypageContent.isSaveEnabled = false
        TabLayoutMediator(binding.tableLayout, binding.mypageContent){ tab, position ->
            if (position == 0) {
                tab.text = "챌린지 후기"
            } else {
                tab.text = "게시판"
            }
        }.attach()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            myPageViewModel.myPageFlow.collect{event->
                when (event) {
                    is MyPageViewModel.MyPageEvent.GetMyData ->{
                        when(event.uiState){
                            is UiState.Success -> {
                                initMyData(event.uiState.data)
                            }
                            is UiState.Loding -> {

                            }
                            is UiState.Error -> {
                                Log.v("MyPage Error", event.toString())
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun initMyData(data: MyData){
        binding.apply {
            myPageNickName.text =  data.nickname
            myPageSubText.text = "리욜드와 함께 지구를 지킨지\n${data.dday}일 되었어요:)"
            badgeCount.text = "${data.badgeAmount}개"
            footPrintCount.text = "${data.totalFootprintAmount}개"
        }
    }

    private fun myInfoEdit(){
        binding.myInfoEdit.setOnClickListener {

        }
    }
    private fun myBadgeData(){
        binding.badgeCount.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_myPageBadgeFragment)
        }
    }
}