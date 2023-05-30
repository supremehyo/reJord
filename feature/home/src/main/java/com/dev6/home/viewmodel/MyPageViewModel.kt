package com.dev6.home.viewmodel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.challenge.ChallengeRes
import com.dev6.domain.model.mypage.BadgeByUidResult
import com.dev6.domain.model.mypage.FootPrintRes
import com.dev6.domain.model.mypage.MyData
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.usecase.mypage.MyPageGetMyBadgeInfoUseCase
import com.dev6.domain.usecase.mypage.MyPageGetMyDataUseCase
import com.dev6.domain.usecase.mypage.MyPageGetMyFootPrintUseCase
import com.dev6.domain.usecase.post.ChallengeListWIthUidUseCase
import com.dev6.domain.usecase.post.PostGetListWithUidUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val postGetListWithUidUserCase: PostGetListWithUidUserCase,
    private val challengeListWIthUidUseCase: ChallengeListWIthUidUseCase,
    private val myPageGetMyDataUseCase: MyPageGetMyDataUseCase,
    private val myPageGetMyFootPrintUseCase: MyPageGetMyFootPrintUseCase,
    private val myPageGetMyBadgeInfoUseCase: MyPageGetMyBadgeInfoUseCase
): ViewModel(){
    private val _myPageFlow = MutableEventFlow<MyPageEvent>()
    val myPageFlow = _myPageFlow.asEventFlow()
    var refreshFlag: MutableLiveData<Boolean> = MutableLiveData()

    var myChallCount = 0
    var myBoardCount = 0

    private suspend fun Event(event : MyPageEvent){
        viewModelScope.launch {
            try {
                _myPageFlow.emit(event)
            }catch (e : Exception){
                Log.e("sdfsdf" , e.message.toString())
            }
        }
    }

    fun postRefreshFlag(refresh : Boolean){
        refreshFlag.value = refresh
    }


    fun clearBoardCount(){
        myBoardCount = 0
    }

    fun clearChallCount(){
        myChallCount = 0
    }

    fun plusChallCount(){
        myChallCount+=1
    }

    fun plusBoardCount(){
        myBoardCount+=1
    }

    suspend fun getPostListWithUid(page : Int, size : Int){
        viewModelScope.launch {
            postGetListWithUidUserCase.getPostListWithUid(page, size).collect{
                Event(MyPageEvent.GetPostListWithUid(it))
            }
        }
    }

     suspend fun getChaalengeListWithUid(page : Int, size : Int){
        viewModelScope.launch {
            challengeListWIthUidUseCase.getChallengeListWithUid(page, size).collect{
                Event(MyPageEvent.GetChallengeListWithUid(it))
            }
        }
    }

    suspend fun getMyData(){
        viewModelScope.launch {
            myPageGetMyDataUseCase.getMyData().collect{
                Event(MyPageEvent.GetMyData(it))
            }
        }
    }

    suspend fun getMyFootPrintList(page:Int,size:Int){
        viewModelScope.launch {
            myPageGetMyFootPrintUseCase.getFootPrintList(page, size).collect{
                Event(MyPageEvent.GetMyFootPrintList(it))
            }
        }
    }

    suspend fun getBadgeInfoList(){
        viewModelScope.launch {
            myPageGetMyBadgeInfoUseCase.getBadgeInfoList().collect{
                Event(MyPageEvent.GetMyBadgeInfoList(it))
            }
        }
    }



    sealed class MyPageEvent{
        data class GetPostListWithUid(val uistate : UiState<PostReadRes>) : MyPageEvent()
        data class GetChallengeListWithUid(val uistate: UiState<ChallengeRes>) : MyPageEvent()
        data class GetMyData(val uiState : UiState<MyData>) : MyPageEvent()
        data class GetMyFootPrintList(val uiState : UiState<FootPrintRes>) : MyPageEvent()
        data class GetMyBadgeInfoList(val uiState : UiState<List<BadgeByUidResult>>) : MyPageEvent()
    }
}