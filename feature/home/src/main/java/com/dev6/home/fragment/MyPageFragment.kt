package com.dev6.home.fragment
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.home.R
import com.dev6.home.databinding.FragmentMyPageBinding
import com.dev6.home.viewmodel.BoardViewModel
import com.dev6.home.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MyPageFragment() : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val myPageViewModel : MyPageViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        repeatOnStarted {
            myPageViewModel.myPageFlow.collect{
                eventHandler(it)
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            myPageViewModel.getPostListWithUid(0,1)
            myPageViewModel.getChaalengeListWithUid(0,1)
        }
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }

    private fun eventHandler(event : MyPageViewModel.MyPageEvent){
        when(event){
            is MyPageViewModel.MyPageEvent.GetPostListWithUid ->{
                when(event.uistate){
                    is UiState.Success ->{
                       Log.v("GetPostListWithUid test" , event.uistate.data.toString())
                    }
                    is UiState.Loding ->{

                    }
                    is UiState.Error ->{

                    }
                }
            }
            is MyPageViewModel.MyPageEvent.GetChallengeListWithUid -> {
                when(event.uistate){
                    is UiState.Success ->{
                        Log.v("GetChallengeListWithUid test" , event.uistate.data.toString())
                    }
                    is UiState.Loding ->{

                    }
                    is UiState.Error ->{

                    }
                }
            }
        }
    }
}