package com.dev6.domain.usecase.join
import android.util.Log
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.usecase.JoinReposBaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


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
            Log.v("asdfsdf" , it.message.toString())
            emit(UiState.Error(it))
        }
    }
}