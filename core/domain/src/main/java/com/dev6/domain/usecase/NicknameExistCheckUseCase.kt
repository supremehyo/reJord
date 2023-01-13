package com.dev6.domain.usecase
import android.util.Log
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.join.nickName.NicknameReq
import com.dev6.domain.model.join.nickName.NicknameUpdateRes
import com.dev6.domain.repository.JoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NicknameExistCheckUseCase @Inject constructor(
    private val joinRepository: JoinRepository
) : NicknameExistCheckBaseUseCase{
    override suspend fun invoke(params: String)= flow {
        emit(UiState.Loding)
        runCatching {
            joinRepository.nicknameExistCheck(params)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}