package com.dev6.domain.usecase.mypage

import com.dev6.common.uistate.UiState
import com.dev6.domain.repository.MyPageRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyPageGetMyDataUseCase @Inject constructor(
    private val myPageRepository: MyPageRepository
){
    fun getMyData() = flow{
        emit(UiState.Loding)
        runCatching {
            myPageRepository.getMyData()
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}