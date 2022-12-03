package com.dev.join
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.usecase.JoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun userJoin(joinReq: JoinReq) = viewModelScope.launch {

    }
}

sealed class Event {
    data class JoinEvent(val uiState: UiState<JoinRes>) : Event()
}