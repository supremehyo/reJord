package com.dev6.domain.usecase.banner
import com.dev6.common.uistate.UiState
import com.dev6.domain.repository.BannerRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BannerGetUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
){
    fun getBannerInfo() = flow {
        emit(UiState.Loding)
        runCatching {
            bannerRepository.getChallengeInfos()
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}