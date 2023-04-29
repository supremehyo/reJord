package com.dev6.domain.usecase.mypage

import com.dev6.common.uistate.UiState
import com.dev6.domain.repository.MyPageRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyPageGetMyBadgeInfoUseCase @Inject constructor(
    private val myPageRepository: MyPageRepository
){
    fun getBadgeInfoList() = flow{
        emit(UiState.Loding)
        runCatching {
            myPageRepository.getBadgeInfoList()
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}