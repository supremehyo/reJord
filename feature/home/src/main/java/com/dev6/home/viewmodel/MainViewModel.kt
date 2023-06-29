package com.dev6.home.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev6.common.uistate.UiState
import com.dev6.core.util.MutableEventFlow
import com.dev6.core.util.asEventFlow
import com.dev6.domain.model.challenge.ChallengeInfoRes
import com.dev6.domain.usecase.banner.BannerGetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bannerGetUseCase: BannerGetUseCase
) : ViewModel() {

    private val _bannereventFlow = MutableEventFlow<HomeEvent>()
    val bannereventFlow = _bannereventFlow.asEventFlow()

    private fun Bannerevent(event: HomeEvent.BannerEvent) {
        viewModelScope.launch {
            try {
                _bannereventFlow.emit(event)
            }catch (e : Exception){
                Log.e("sdfsdf" , e.message.toString())
            }
        }
    }

    suspend fun getBannerData(){
        viewModelScope.launch {
            bannerGetUseCase.getBannerInfo().collect{ uiState ->
                Bannerevent(HomeEvent.BannerEvent(uiState))
            }
        }
    }


    sealed class HomeEvent {
        data class BannerEvent(val uiState : UiState<ChallengeInfoRes>) : HomeEvent()
    }
}