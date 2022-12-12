package com.dev6.join
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.usecase.JoinReposBaseUseCase
import com.dev6.domain.usecase.JoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }


    fun userJoin(joinReq: JoinReq) {
        viewModelScope.launch{
            joinUseCase(joinReq).catch {

            }.collect{ uiState ->
                event(Event.UiEvent(uiState))
            }
        }
    }



    sealed class Event {
        data class UiEvent(val uiState: UiState<JoinRes>) : Event()
    }
}

