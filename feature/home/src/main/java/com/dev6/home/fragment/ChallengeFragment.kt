package com.dev6.home.fragment
import android.os.Parcelable
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.enums.ScrollType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.challenge.ChallengeReadReq
import com.dev6.domain.model.challenge.ChallengeReviewResult
import com.dev6.home.R
import com.dev6.home.adapter.ChallengeRecyclerAdapter
import com.dev6.home.databinding.FragmentChallengeBinding
import com.dev6.home.viewmodel.ChallengeViewModel
import java.time.LocalDateTime


class ChallengeFragment : BindingFragment<FragmentChallengeBinding>(R.layout.fragment_challenge) {
    val challengeViewModel : ChallengeViewModel by activityViewModels()
    lateinit var challengeRc : RecyclerView
    lateinit var challengeRecyclerAdapter: ChallengeRecyclerAdapter
    private  var mutableList: MutableList<ChallengeReviewResult> = arrayListOf()
    var count = 0
    var index = 0
    var tempIndex = 0
    private var recyclerViewState: Parcelable? = null

    var totalElements = 0

    override fun initView() {
        super.initView()
        challengeViewModel.clearChallCount()
        challengeRc = binding.challengeRc
        count = challengeViewModel.challCount


        challengeRecyclerAdapter = ChallengeRecyclerAdapter("DEFAULT",mutableList, {

        },{
            //총 갯수가 현재 페이지 * 5 보다 많으면 더 불러올 수 있으니 count 를 늘리고 불러온다.
            if(totalElements > challengeViewModel.challCount * 5){
                index = it
                challengeViewModel.plusChallCount()
                repeatOnStarted {
                    challengeViewModel.getChallengeList(ChallengeReadReq(
                        challengeViewModel.challCount,
                        LocalDateTime.now().toString(),
                        5
                    ))
                }

            }
        },{})
        challengeRc.apply {
            adapter = challengeRecyclerAdapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(scrollCheck(this))
        }

    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            challengeViewModel.getChallengeList(ChallengeReadReq(
                0,
                LocalDateTime.now().toString(),
                5
            ))
        }

        challengeViewModel.refreshFlag.observe(viewLifecycleOwner){
            if(it == true){
                index =0
                mutableList.clear()
                challengeRecyclerAdapter.notifyDataSetChanged()
                challengeViewModel.postRefreshFlag(false)
            }
        }

    }

    override fun initListener() {
        super.initListener()

        challengeRc.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStartedFragment {
            challengeViewModel.ChallengeEventFlow.collect{
                eventHandler(it)
            }
        }
        challengeViewModel.upScrollFlag.observe(this) {
            when (it) {
                true -> {
                    challengeRc.scrollToPosition(0)
                    challengeViewModel.upScrollDone()
                }
                else -> {}
            }
        }
    }

    private fun scrollCheck(rc: RecyclerView): RecyclerView.OnScrollListener {
        val onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //위로 스크롤 했을 때
                if (dy < 0) {
                    challengeViewModel.updateScrollState(ScrollType.SCROLLUP)
                } else {
                    challengeViewModel.updateScrollState(ScrollType.SCROLLDONW)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //최상단
                if (!rc.canScrollVertically(-1)) {
                    challengeViewModel.updateScrollState(ScrollType.TOP)
                }
            }
        }
        return onScrollListener
    }

    private fun eventHandler(event : ChallengeViewModel.ChallengeEvent){
            if (event is ChallengeViewModel.ChallengeEvent.GetChallengeUiEvent){
                when(event.uiState){
                    is UiState.Loding ->{

                    }
                    is UiState.Success ->{
                        Log.v("챌린지 테스트" , event.uiState.data.toString())
                        mutableList.addAll(index,event.uiState.data.content)
                        totalElements = event.uiState.data.totalElements
                        recyclerViewState = challengeRc.layoutManager?.onSaveInstanceState()
                        //challengeRecyclerAdapter.notifyItemRangeChanged(0,5)
                        challengeRc.layoutManager?.onRestoreInstanceState(recyclerViewState)
                    }
                    is UiState.Error ->{
                        Log.v("챌린지 테스트  에러" , event.uiState.error!!.message.toString())
                        if(event.uiState.error!!.message.toString() == "인증실패"){
                            //Toast.makeText(requireContext(), "자동 로그인 만료", Toast.LENGTH_SHORT).show()
                           // findNavController().popBackStack()
                        }
                    }
                }
            }
    }

    override fun onResume() {
        super.onResume()
        Log.v("resum","asdfsd")
    }

}