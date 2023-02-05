package com.dev6.home.fragment
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.NonNull
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.enums.ScrollType
import com.dev6.domain.model.challenge.ChallengeReadReq
import com.dev6.home.R
import com.dev6.home.adapter.ChallengeRecyclerAdapter
import com.dev6.home.databinding.FragmentChallengeBinding
import com.dev6.home.viewmodel.ChallengeViewModel
import com.dev6.home.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect

class ChallengeFragment : BindingFragment<FragmentChallengeBinding>(R.layout.fragment_challenge) {
    val challengeViewModel : ChallengeViewModel by activityViewModels()
    lateinit var challengeRc : RecyclerView
    lateinit var challengeRecyclerAdapter: ChallengeRecyclerAdapter

    override fun initView() {
        super.initView()
        challengeRecyclerAdapter = ChallengeRecyclerAdapter {

        }
        challengeRc = binding.challengeRc
        challengeRc.apply {
            adapter = challengeRecyclerAdapter
            layoutManager = LinearLayoutManager(context)
           // addOnScrollListener(scrollCheck(this))
        }

    }

    override fun initViewModel() {
        super.initViewModel()
        challengeViewModel.getChallengeList(ChallengeReadReq(
            0,
            "2023-01-28T23:16:59",
            50
        ))
    }

    override fun initListener() {
        super.initListener()

        //CollapsingToolbarLayout 랑 같이 리사이클러뷰 쓰니까 스크롤이 안되는 문제가 있어서 이걸로 해결
        challengeRc.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
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
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStartedFragment {
            challengeViewModel.ChallengeEventFlow.collect{
                eventHandler(it)
            }
        }
    }

    private fun eventHandler(event : ChallengeViewModel.ChallengeEvent){
        when(event){
            is ChallengeViewModel.ChallengeEvent.GetChallengeUiEvent ->{
                when(event.uiState){
                    is UiState.Loding ->{

                    }
                    is UiState.Success ->{
                        Log.v("챌린지 테스트" , event.uiState.data.toString())
                        challengeRecyclerAdapter.submitList(event.uiState.data.content)
                    }
                    is UiState.Error ->{
                        Log.v("챌린지 테스트  에러" , event.uiState.error.toString())
                    }
                }
            }
        }
    }

    /*
    fun scrollCheck(rc : RecyclerView) : RecyclerView.OnScrollListener {
        val onScrollListener = object: RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx:Int, dy:Int) {
                super.onScrolled(recyclerView, dx, dy)
                //위로 스크롤 했을 때
                if(dy<0){
                     //boardViewModel.updateScrollState(ScrollType.SCROLLUP)
                }else{
                    //boardViewModel.updateScrollState(ScrollType.SCROLLDONW)
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //최상단
                if(!rc.canScrollVertically(-1)) {
                    //boardViewModel.updateScrollState(ScrollType.TOP)
                }
            }
        }
        return onScrollListener
    }*/
}