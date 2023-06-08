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
import com.dev6.domain.usecase.write.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val writeUseCase: WriteUseCase,
    private val challengeWriteUseCase: ChallengeWriteUseCase,

) : ViewModel(){
    private val _categoryLiveData = MutableLiveData<WriteType>()
    val categoryLiveData : LiveData<WriteType>
        get() = _categoryLiveData

    private val _challengeId = MutableLiveData<String>()
    val challengeId : LiveData<String>
        get() = _challengeId


    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _writeType = MutableLiveData<String>()
    val writeType : LiveData<String>
        get() = _writeType

    init {
        _categoryLiveData.value = WriteType.SHARE
    }
    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun changeWriteType(type : String){
        _writeType.value = type
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
    }
}