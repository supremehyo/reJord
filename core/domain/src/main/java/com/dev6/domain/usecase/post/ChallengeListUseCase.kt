package com.dev6.domain.usecase.post
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.challenge.ChallengeReadReq
import com.dev6.domain.repository.PostRepository
import com.dev6.domain.usecase.ChallengeGetListReposBaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChallengeListUseCase @Inject constructor(
    private val postRepository: PostRepository
) : ChallengeGetListReposBaseUseCase{
    override suspend fun invoke(params: ChallengeReadReq) = flow {
        emit(UiState.Loding)
        runCatching {
            postRepository.getChallengeList(params.page,params.requestTime,params.size)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}