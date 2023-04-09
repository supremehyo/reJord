package com.dev6.home.fragment

import android.util.Log
import android.view.MotionEvent
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.challenge.ChallengeReadReq
import com.dev6.domain.model.challenge.ChallengeReviewResult
import com.dev6.home.R
import com.dev6.home.adapter.ChallengeRecyclerAdapter
import com.dev6.home.databinding.FragmentMyPageChallengeBinding
import com.dev6.home.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class MyPageChallengeFragment :
    BindingFragment<FragmentMyPageChallengeBinding>(R.layout.fragment_my_page_challenge) {
    private val myPageViewModel: MyPageViewModel by activityViewModels()
    private lateinit var MychallengeRc: RecyclerView
    private lateinit var challengeRecyclerAdapter: ChallengeRecyclerAdapter
    private var mutableList: MutableList<ChallengeReviewResult> = arrayListOf()
    private var count = 0
    private var index = 0

    override fun initView() {
        super.initView()
        //recyclerView init
        myPageViewModel.clearChallCount()
        MychallengeRc = binding.MychallengeRc
        count = myPageViewModel.myChallCount

        //확인필요
        MychallengeRc.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action = e.action
                when (action) {
                    MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        repeatOnStarted {
            myPageViewModel.myPageFlow.collect { event ->
                when (event) {
                    is MyPageViewModel.MyPageEvent.GetChallengeListWithUid -> {
                        when (event.uistate) {
                            is UiState.Success -> {
                                Log.v("GetChallengeListWithUid test", event.uistate.data.toString())
                                mutableList.addAll(index, event.uistate.data.content)
                                challengeRecyclerAdapter = ChallengeRecyclerAdapter("MYPAGE",mutableList,
                                    {  //클릭 이벤트

                                    },
                                    {
                                        if (event.uistate.data.totalElements > myPageViewModel.myChallCount * 5) {
                                            index = it
                                            myPageViewModel.plusChallCount()
                                            lifecycleScope.launch(Dispatchers.IO) {
                                                myPageViewModel.getChaalengeListWithUid(
                                                    myPageViewModel.myChallCount, 5)
                                            }

                                        }
                                    })
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
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            myPageViewModel.getChaalengeListWithUid(0, 1)
        }
    }

    override fun initListener() {
        super.initListener()

    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }
}