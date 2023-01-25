package com.dev6.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel(){
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        //data class UiEvent(val uiState: UiState<>) : Event()
    }
}