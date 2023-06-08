package com.dev6.home.viewmodel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.challenge.ChallengeEditReq
import com.dev6.domain.model.challenge.ChallengeEditRes
import com.dev6.domain.model.challenge.ChallengeRes
import com.dev6.domain.model.mypage.BadgeByUidResult
import com.dev6.domain.model.mypage.FootPrintRes
import com.dev6.domain.model.mypage.MyData
import com.dev6.domain.model.post.delete.PostEditReq
import com.dev6.domain.model.post.delete.PostEditRes
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.usecase.mypage.MyPageGetMyBadgeInfoUseCase
import com.dev6.domain.usecase.mypage.MyPageGetMyDataUseCase
import com.dev6.domain.usecase.mypage.MyPageGetMyFootPrintUseCase
import com.dev6.domain.usecase.post.ChallengeEditUseCase
import com.dev6.domain.usecase.post.ChallengeListWIthUidUseCase
import com.dev6.domain.usecase.post.PostGetListWithUidUserCase
import com.dev6.domain.usecase.write.ChallengeDeleteUseCase
import com.dev6.domain.usecase.write.PostDeleteUseCase
import com.dev6.domain.usecase.write.PostEditUseCase
import com.dev6.write.viewmodel.WriteViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val postGetListWithUidUserCase: PostGetListWithUidUserCase,
    private val challengeListWIthUidUseCase: ChallengeListWIthUidUseCase,
    private val myPageGetMyDataUseCase: MyPageGetMyDataUseCase,
    private val myPageGetMyFootPrintUseCase: MyPageGetMyFootPrintUseCase,
    private val myPageGetMyBadgeInfoUseCase: MyPageGetMyBadgeInfoUseCase,
    private val postDeleteUseCase : PostDeleteUseCase,
    private val postEditUseCase : PostEditUseCase,
    private val challengeEditUseCase : ChallengeEditUseCase,
    private val challengeDeleteUseCase: ChallengeDeleteUseCase,
): ViewModel(){


    private val _myPageFlow = MutableEventFlow<MyPageEvent>()
    val myPageFlow = _myPageFlow.asEventFlow()

    private val _myEditFlow = MutableEventFlow<MyEditEvent>()
    val myEditFlow = _myEditFlow.asEventFlow()

    var postRefreshFlag: MutableLiveData<Boolean> = MutableLiveData()
    var challengeRefreshFlag: MutableLiveData<Boolean> = MutableLiveData()

    var myChallCount = 0
    var myBoardCount = 0

    private fun Event(event : MyPageEvent){
        viewModelScope.launch {
            _myPageFlow.emit(event)
        }
    }

    private fun EditEvent(event : MyEditEvent){
        viewModelScope.launch {
            _myEditFlow.emit(event)
        }
    }

    fun postRefreshFlag(refresh : Boolean){
        postRefreshFlag.postValue(refresh)
    }

    fun challengeRefreshFlag(refresh : Boolean){
        challengeRefreshFlag.postValue(refresh)
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
        viewModelScope.launch(Dispatchers.IO) {
            postGetListWithUidUserCase.getPostListWithUid(page, size).collect{
                Event(MyPageEvent.GetPostListWithUid(it))
            }
        }
    }

     suspend fun getChaalengeListWithUid(page : Int, size : Int){
        viewModelScope.launch(Dispatchers.IO) {
            challengeListWIthUidUseCase.getChallengeListWithUid(page, size).collect{
                Event(MyPageEvent.GetChallengeListWithUid(it))
            }
        }
    }

    suspend fun getMyData(){
        viewModelScope.launch(Dispatchers.IO) {
            myPageGetMyDataUseCase.getMyData().collect{
                Event(MyPageEvent.GetMyData(it))
            }
        }
    }

    suspend fun getMyFootPrintList(page:Int,size:Int){
        viewModelScope.launch(Dispatchers.IO) {
            myPageGetMyFootPrintUseCase.getFootPrintList(page, size).collect{
                Event(MyPageEvent.GetMyFootPrintList(it))
            }
        }
    }

    suspend fun getBadgeInfoList(){
        viewModelScope.launch(Dispatchers.IO) {
            myPageGetMyBadgeInfoUseCase.getBadgeInfoList().collect{
                Event(MyPageEvent.GetMyBadgeInfoList(it))
            }
        }
    }

    suspend fun challengeDelete(challengeReviewId : String){
        viewModelScope.launch(Dispatchers.IO) {
            challengeDeleteUseCase(challengeReviewId).collect{ uiState->
                EditEvent(MyEditEvent.deleteChallengeEvent(uiState))
            }
        }
    }

    suspend fun deletePost(postId : String){
        viewModelScope.launch(Dispatchers.IO) {
            postDeleteUseCase(postId).collect{uiState->
                EditEvent(MyEditEvent.deletePostEvent(uiState))
            }
        }
    }

    suspend fun editPost(postEditReq : PostEditReq){
        viewModelScope.launch(Dispatchers.IO) {
            postEditUseCase(postEditReq).collect{uiState->
                EditEvent(MyEditEvent.editPostEvent(uiState))
            }
        }
    }

    suspend fun editChallenge(challengeEditReq : ChallengeEditReq){
        viewModelScope.launch(Dispatchers.IO) {
            challengeEditUseCase(challengeEditReq).collect{uiState->
                EditEvent(MyEditEvent.editChallengeEvent(uiState))
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

    sealed class MyEditEvent{
        data class editPostEvent(val uiState: UiState<PostEditRes>) : MyEditEvent()
        data class deletePostEvent(val uiState: UiState<String>) : MyEditEvent()
        data class deleteChallengeEvent(val uiState: UiState<String>) : MyEditEvent()
        data class editChallengeEvent(val uiState: UiState<ChallengeEditRes>) :MyEditEvent()
    }
}