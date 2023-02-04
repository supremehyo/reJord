package com.dev6.write.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.enums.WriteType
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.join.login.LoginRes
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.domain.model.post.write.PostWriteRes
import com.dev6.domain.usecase.write.WriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val writeUseCase: WriteUseCase
) : ViewModel(){
    private val _categoryLiveData = MutableLiveData<WriteType>()
    val categoryLiveData : LiveData<WriteType>
        get() = _categoryLiveData

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
    fun updateCateGoryValue(type : WriteType){
        _categoryLiveData.value = type
    }
    fun postWrite(dto : PostWriteReq){
        viewModelScope.launch {
            writeUseCase(dto).catch {}.collect{ uiState ->
                event(Event.postWriteEvent(uiState))
            }
        }
    }

    sealed class Event {
        data class postWriteEvent(val uiState: UiState<PostWriteRes>) : Event()
    }
}