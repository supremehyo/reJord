package com.dev6.home.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.model.post.read.PostReadReq
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.usecase.post.PostGetListUserCase
import com.dev6.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postGetListUserCase: PostGetListUserCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<HomeEvent>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun event(event: HomeEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

     fun getPostList(postReadReq: PostReadReq) {
        viewModelScope.launch {
            postGetListUserCase(postReadReq).catch {}.collect { uiState ->
                event(HomeEvent.GetPostUiEvent(uiState))
            }
        }
    }

    fun getPostList2(postReadReq: PostReadReq) {
        viewModelScope.launch {
            postGetListUserCase(postReadReq).catch {}.collect { uiState ->
                event(HomeEvent.GetChallengeUiEvent(uiState))
            }
        }
    }

    sealed class HomeEvent {
        data class GetPostUiEvent(val uiState: UiState<PostReadRes>) : HomeEvent()
        data class GetChallengeUiEvent(val uiState: UiState<PostReadRes>) : HomeEvent()
    }
}