package com.dev6.login
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.domain.model.join.login.LoginRes
import com.dev6.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(){

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    suspend fun userLogin(loginReq: LoginReq) {
        viewModelScope.launch{
            loginUseCase(loginReq).catch {}.collect{ uiState ->
                event(Event.UiEvent(uiState))
            }
        }
    }

    sealed class Event {
        data class UiEvent(val uiState: UiState<LoginRes>) : Event()
    }

}