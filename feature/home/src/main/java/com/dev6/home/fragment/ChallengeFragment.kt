package com.dev6.home.fragment
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.dev6.common.uistate.UiState
import com.dev6.core.base.BindingFragment
import com.dev6.domain.model.post.read.PostReadReq
import com.dev6.home.R
import com.dev6.home.databinding.FragmentChallengeBinding
import com.dev6.home.viewmodel.MainViewModel

class ChallengeFragment : BindingFragment<FragmentChallengeBinding>(R.layout.fragment_challenge) {
    val mainViewModel : MainViewModel by activityViewModels()
    override fun initView() {
        super.initView()

    }

    override fun initViewModel() {
        super.initViewModel()

    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        /*
        repeatOnStartedFragment {
            mainViewModel.eventFlow.collect{ event->
                eventHandler(event)
            }
        }

         */
    }

    private fun eventHandler(event : MainViewModel.HomeEvent){
        when(event){
            is MainViewModel.HomeEvent.GetChallengeUiEvent ->{
                when(event.uiState){
                    is UiState.Loding ->{

                    }
                    is UiState.Success ->{
                        Log.v("챌린지 테스트" , event.uiState.data.toString())
                    }
                    is UiState.Error ->{

                    }
                }
            }
            is MainViewModel.HomeEvent.GetPostUiEvent ->{
                when(event.uiState){
                    is UiState.Loding ->{

                    }
                    is UiState.Success ->{
                        Log.v("게시글 테스트2" , event.uiState.data.toString())
                    }
                    is UiState.Error ->{

                    }
                }
            }
        }
    }
}