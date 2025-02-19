package com.dev6.home.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.enums.ScrollType
import com.dev6.core.enums.WriteType
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.challenge.ChallengeReadReq
import com.dev6.domain.model.challenge.ChallengeRes
import com.dev6.domain.model.post.read.PostReadReq
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.usecase.post.ChallengeListUseCase
import com.dev6.domain.usecase.post.PostGetListUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val challengeListUseCase: ChallengeListUseCase
) : ViewModel() {

    private val _ChallengeEventFlow = MutableEventFlow<ChallengeEvent>(10)
    val ChallengeEventFlow = _ChallengeEventFlow.asEventFlow()
    var scrollFlag: MutableLiveData<ScrollType> = MutableLiveData()
    var upScrollFlag: MutableLiveData<Boolean> = MutableLiveData()
    var refreshFlag: MutableLiveData<Boolean> = MutableLiveData()
    var challCount = 0

    private fun ChallengeEvent(event: ChallengeEvent) {
        viewModelScope.launch {
            _ChallengeEventFlow.emit(event)
        }
    }

    fun postRefreshFlag(refresh : Boolean){
        refreshFlag.postValue(refresh)
    }

    fun clearChallCount(){
        challCount = 0
    }

    fun plusChallCount(){
        challCount+=1
    }

    fun upScroll(){
        upScrollFlag.value = true
    }

    fun upScrollDone(){
        upScrollFlag.value = false
    }

    fun updateScrollState(state : ScrollType){
        scrollFlag.value = state
    }

    suspend fun getChallengeList(challengeReadReq: ChallengeReadReq) {
        viewModelScope.launch(Dispatchers.IO) {
            challengeListUseCase(challengeReadReq).collect{ uiState ->
                ChallengeEvent(ChallengeEvent.GetChallengeUiEvent(uiState))
            }
        }
    }

    sealed class ChallengeEvent {
        data class GetChallengeUiEvent(val uiState: UiState<ChallengeRes>) : ChallengeEvent()
    }
}