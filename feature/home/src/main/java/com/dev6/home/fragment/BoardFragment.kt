package com.dev6.home.fragment
import android.os.Parcelable
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.NonNull
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.enums.PostType
import com.dev6.core.enums.ScrollType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.post.read.Content
import com.dev6.domain.model.post.read.PostReadReq
import com.dev6.home.R
import com.dev6.home.adapter.BoardRecyclerAdapter
import com.dev6.home.databinding.FragmentBoardBinding
import com.dev6.home.viewmodel.BoardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime


class BoardFragment : BindingFragment<FragmentBoardBinding>(R.layout.fragment_board) {
    val boardViewModel: BoardViewModel by activityViewModels()
    lateinit var boardRc: RecyclerView
    lateinit var boardRecyclerAdapter: BoardRecyclerAdapter
    private  var mutableList : MutableList<Content> = arrayListOf()
    var count = 0
    var index = 0
    var postType = ""
    private var recyclerViewState: Parcelable? = null
    lateinit var job : Job

    override fun initView() {
        super.initView()
        boardViewModel.clearBoardCount()
        boardRc = binding.boardRecyclerView


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

        binding.boardChipGroup.check(R.id.allChip)
        //카테고리 필터링 리스너
        binding.boardChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.allChip -> {
                    boardViewModel.changePostType("")
                }
                R.id.sharedChip -> {
                    boardViewModel.changePostType(PostType.SHARE.toString())
                }
                R.id.etcChip -> {
                    boardViewModel.changePostType(PostType.OTHERS.toString())
                }
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            boardViewModel.getPostList(
                PostReadReq(0, postType , LocalDateTime.now().toString(), 5)
            )
        }

        boardViewModel.postType.observe(viewLifecycleOwner){
            postType = it
            index = 0
            mutableList.clear()
            boardViewModel.clearBoardCount()
            repeatOnStarted {
                boardViewModel.getPostList(
                    PostReadReq(0, postType,
                        LocalDateTime.now().toString(), 5)
                )
            }
        }
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            boardViewModel.BoardeventFlow.collect { event ->
                eventHandler(event)
            }
        }
        //상위 fragment 에 있는 fab 버튼의 이벤트를 받기위해 구현
        boardViewModel.upScrollFlag.observe(this) {
            when (it) {
                true -> {
                    boardRc.scrollToPosition(0)
                    boardViewModel.upScrollDone()
                }
                else -> {}
            }
        }
    }

    fun scrollCheck(rc: RecyclerView): RecyclerView.OnScrollListener {
        val onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //위로 스크롤 했을 때
                if (dy < 0) {
                    boardViewModel.updateScrollState(ScrollType.SCROLLUP)
                } else {
                    boardViewModel.updateScrollState(ScrollType.SCROLLDONW)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //최상단
                if (!rc.canScrollVertically(-1)) {
                    boardViewModel.updateScrollState(ScrollType.TOP)
                }
            }
        }
        return onScrollListener
    }

    private fun eventHandler(event: BoardViewModel.BoardEvent) {
        when (event) {
            //게시글 데이터
            is BoardViewModel.BoardEvent.GetPostUiEvent -> {
                when (event.uiState) {
                    is UiState.Loding -> {}
                    is UiState.Success -> {
                        Log.v("testtt", event.uiState.data.content.toString())
                        mutableList.addAll(index,event.uiState.data.content)
                        boardRecyclerAdapter = BoardRecyclerAdapter("DEFAULT",mutableList,{


                        }, {
                            if(event.uiState.data.totalElements > boardViewModel.boardCount * 5){
                                index = it
                                boardViewModel.plusBoardCount()
                                lifecycleScope.launch(Dispatchers.IO) {
                                    boardViewModel.getPostList(
                                        PostReadReq(boardViewModel.boardCount, postType,
                                            LocalDateTime.now().toString(), 5)
                                    )
                                }
                            }
                        },{

                        })
                        recyclerViewState = boardRc.layoutManager?.onSaveInstanceState()
                        boardRc.apply {
                            adapter = boardRecyclerAdapter
                            layoutManager = LinearLayoutManager(context)
                            addOnScrollListener(scrollCheck(this))
                        }
                        boardRc.layoutManager?.onRestoreInstanceState(recyclerViewState)
                    }
                    is UiState.Error -> {
                        Log.e("sdfsdf" , event.uiState.error.toString())
                    }
                }
            }
            else -> {

            }
        }
    }



    override fun onPause() {
        super.onPause()
       // job.cancel()
    }
}