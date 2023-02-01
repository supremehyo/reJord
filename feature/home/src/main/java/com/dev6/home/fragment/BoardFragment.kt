package com.dev6.home.fragment
import android.util.Log
import android.view.MotionEvent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.dev6.common.uistate.UiState
import com.dev6.core.base.BindingFragment
import com.dev6.domain.model.post.read.PostReadReq
import com.dev6.home.R
import com.dev6.home.adapter.BoardRecyclerAdapter
import com.dev6.home.databinding.FragmentBoardBinding
import com.dev6.home.viewmodel.BoardViewModel


class BoardFragment : BindingFragment<FragmentBoardBinding>(R.layout.fragment_board) {
    val boardViewModel : BoardViewModel by activityViewModels()
    lateinit var boardRc : RecyclerView
    lateinit var boardRecyclerAdapter: BoardRecyclerAdapter

    override fun initView() {
        super.initView()

        boardRecyclerAdapter = BoardRecyclerAdapter{
            // 게시글 클릭
        }
        boardRc = binding.boardRecyclerView
        boardRc.apply {
            adapter = boardRecyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }

        //CollapsingToolbarLayout 랑 같이 리사이클러뷰 쓰니까 스크롤이 안되는 문제가 있어서 이걸로 해결
        boardRc.addOnItemTouchListener(object : OnItemTouchListener {
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

    override fun initViewModel() {
        super.initViewModel()
        boardViewModel.getPostList(
            // LocalDateTime.now().toString()
            PostReadReq(0, "2023-01-28T23:16:59",5)
        )
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStartedFragment {
            boardViewModel.BoardeventFlow.collect{ event->
                eventHandler(event)
            }
        }
        //상위 fragment 에 있는 fab 버튼의 이벤트를 받기위해 구현
        boardViewModel.upScrollFlag.observe(this) {
            when(it){
                true ->{
                    boardRc.scrollToPosition(0)
                    boardViewModel.upScrollDone()
                }
                else ->{}
            }
        }


    }

    private fun eventHandler(event : BoardViewModel.BoardEvent){
        when(event){
            //게시글 데이터
            is BoardViewModel.BoardEvent.GetPostUiEvent ->{
                when(event.uiState){
                    is UiState.Loding ->{

                    }
                    is UiState.Success ->{
                        Log.v("게시글 테스트",event.uiState.data.content.size.toString())
                        boardRecyclerAdapter.submitList(event.uiState.data.content)
                    }
                    is UiState.Error ->{

                    }
                }
            }else ->{

            }
        }
    }
}