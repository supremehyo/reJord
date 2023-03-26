package com.dev6.home.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.challenge.ChallengeRes
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.usecase.post.ChallengeListWIthUidUseCase
import com.dev6.domain.usecase.post.PostGetListWithUidUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val postGetListWithUidUserCase: PostGetListWithUidUserCase,
    private val challengeListWIthUidUseCase: ChallengeListWIthUidUseCase
): ViewModel(){
    private val _myPageFlow = MutableEventFlow<MyPageEvent>()
    val myPageFlow = _myPageFlow.asEventFlow()


    private suspend fun Event(event : MyPageEvent){
        viewModelScope.launch {
            _myPageFlow.emit(event)
        }
    }

    //둘다 suspend 처리하니까 동시 호출가능 나머지도 다 수정필요
    suspend fun getPostListWithUid(page : Int, size : Int){
        viewModelScope.launch {
            postGetListWithUidUserCase.getPostListWithUid(page, size).collect{
                Event(MyPageEvent.GetPostListWithUid(it))
            }
        }
    }
    //둘다 suspend 처리하니까 동시 호출가능 나머지도 다 수정필요
     suspend fun getChaalengeListWithUid(page : Int, size : Int){
        viewModelScope.launch {
            challengeListWIthUidUseCase.getChallengeListWithUid(page, size).collect{
                Event(MyPageEvent.GetChallengeListWithUid(it))
            }
        }
    }



    sealed class MyPageEvent{
        data class GetPostListWithUid(val uistate : UiState<PostReadRes>) : MyPageEvent()
        data class GetChallengeListWithUid(val uistate: UiState<ChallengeRes>) : MyPageEvent()
    }
}