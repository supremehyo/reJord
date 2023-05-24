package com.dev6.write.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.enums.WriteType
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.challenge.ChallengeEditReq
import com.dev6.domain.model.challenge.ChallengeEditRes
import com.dev6.domain.model.post.delete.PostEditReq
import com.dev6.domain.model.post.delete.PostEditRes
import com.dev6.domain.model.post.write.ChallengeWriteReq
import com.dev6.domain.model.post.write.ChallengeWriteRes
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.domain.model.post.write.PostWriteRes
import com.dev6.domain.usecase.post.ChallengeEditUseCase
import com.dev6.domain.usecase.write.ChallengeWriteUseCase
import com.dev6.domain.usecase.write.PostDeleteUseCase
import com.dev6.domain.usecase.write.PostEditUseCase
import com.dev6.domain.usecase.write.WriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val writeUseCase: WriteUseCase,
    private val postDeleteUseCase : PostDeleteUseCase,
    private val postEditUseCase : PostEditUseCase,
    private val challengeEditUseCase : ChallengeEditUseCase,
    private val challengeWriteUseCase: ChallengeWriteUseCase
) : ViewModel(){
    private val _categoryLiveData = MutableLiveData<WriteType>()
    val categoryLiveData : LiveData<WriteType>
        get() = _categoryLiveData

    private val _challengeId = MutableLiveData<String>()
    val challengeId : LiveData<String>
        get() = _challengeId


    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    init {
        _categoryLiveData.value = WriteType.SHARE
    }
    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }



    fun changeChallengeIdData(id : String){
        _challengeId.value = id
    }

    fun updateCateGoryValue(type : WriteType){
        _categoryLiveData.value = type
    }

    fun getCateGoryValue() : WriteType?{
        return _categoryLiveData.value
    }

    fun postWrite(dto : PostWriteReq){
        viewModelScope.launch {
            writeUseCase(dto).catch {}.collect{ uiState ->
                event(Event.postWriteEvent(uiState))
            }
        }
    }

    fun deletePost(postId : String){
        viewModelScope.launch {
            postDeleteUseCase(postId).collect{uiState->
                event(Event.deletePostEvent(uiState))
            }
        }
    }

    fun editPost(postEditReq : PostEditReq){
        viewModelScope.launch {
            postEditUseCase(postEditReq).collect{uiState->
                event(Event.editPostEvent(uiState))
            }
        }
    }

    fun editChallenge(challengeEditReq : ChallengeEditReq){
        viewModelScope.launch {
            challengeEditUseCase(challengeEditReq).collect{uiState->
                event(Event.editChallengeEvent(uiState))
            }
        }
    }

    fun initWriteData(){
        _categoryLiveData.value = WriteType.SHARE
    }

    fun challengeWrite(dto : ChallengeWriteReq){
        viewModelScope.launch {
            challengeWriteUseCase(dto).catch { }.collect{ uiState->
                event(Event.postChallegeEvent(uiState))
            }
        }
    }

    sealed class Event {
        data class postWriteEvent(val uiState: UiState<PostWriteRes>) : Event()
        data class postChallegeEvent(val uiState: UiState<ChallengeWriteRes>) : Event()
        data class deletePostEvent(val uiState: UiState<String>) : Event()
        data class editPostEvent(val uiState: UiState<PostEditRes>) : Event()
        data class editChallengeEvent(val uiState: UiState<ChallengeEditRes>) : Event()
    }
}