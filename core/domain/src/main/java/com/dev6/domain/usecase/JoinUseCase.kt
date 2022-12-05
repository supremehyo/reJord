package com.dev6.domain.usecase
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.repository.JoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias JoinReposBaseUseCase = BaseUseCase<JoinReq, Flow<UiState<JoinRes>>>

class JoinUseCase @Inject constructor(
    private val joinRepository: JoinRepository
): JoinReposBaseUseCase {
    override suspend fun invoke(joinReq: JoinReq) = flow {
        emit(UiState.Loding)
        runCatching {
            joinRepository.signUp(joinReq)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}