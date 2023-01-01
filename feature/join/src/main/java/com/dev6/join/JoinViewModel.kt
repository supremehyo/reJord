package com.dev6.join
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.model.join.nickName.NicknameExistCheckRes
import com.dev6.domain.model.join.nickName.NicknameReq
import com.dev6.domain.model.join.nickName.NicknameUpdateRes
import com.dev6.domain.usecase.JoinReposBaseUseCase
import com.dev6.domain.usecase.JoinUpdateUseCase
import com.dev6.domain.usecase.JoinUseCase
import com.dev6.domain.usecase.NicknameExistCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase,
    private val userDataUpdateUseCase: JoinUpdateUseCase,
    private val userNicknameExistCheckUseCase: NicknameExistCheckUseCase
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
            joinUseCase(joinReq).catch {}.collect{ uiState ->
                event(Event.UiEvent(uiState))
            }
        }
    }

    fun userDataUpdate(updateReq: NicknameReq , userUid: String) {
        viewModelScope.launch{
            userDataUpdateUseCase(Pair(updateReq , userUid)).catch {}.collect{ uiState ->
                event(Event.userDataUpdateUiEvent(uiState))
            }
        }
    }


    fun userUserIdExistCheck(nickname: String) {
        viewModelScope.launch{
            userNicknameExistCheckUseCase(nickname).catch {}.collect{ uiState ->
                event(Event.userUserIdExistUiEvent(uiState))
            }
        }
    }

    sealed class Event {
        data class UiEvent(val uiState: UiState<JoinRes>) : Event()
        data class userDataUpdateUiEvent(val uiState: UiState<NicknameUpdateRes>) : Event()
        data class userUserIdExistUiEvent(val uiState: UiState<NicknameExistCheckRes>) : Event()
    }
}

