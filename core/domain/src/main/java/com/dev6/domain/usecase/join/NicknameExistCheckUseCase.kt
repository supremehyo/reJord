package com.dev6.domain.usecase.join
import com.dev6.common.uistate.UiState
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.usecase.NicknameExistCheckBaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NicknameExistCheckUseCase @Inject constructor(
    private val joinRepository: JoinRepository
) : NicknameExistCheckBaseUseCase {
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