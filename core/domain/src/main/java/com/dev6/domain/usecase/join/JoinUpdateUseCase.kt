package com.dev6.domain.usecase.join
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.join.nickName.NicknameReq
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.usecase.JoinUpdateReposBaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class JoinUpdateUseCase @Inject constructor(
    private val joinRepository: JoinRepository
) : JoinUpdateReposBaseUseCase {
    override suspend fun invoke(pair: Pair<NicknameReq,String>)= flow {
        emit(UiState.Loding)
        runCatching {
            joinRepository.joinUpdate(pair)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}