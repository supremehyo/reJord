package com.dev6.domain.usecase.post

import com.dev6.common.uistate.UiState
import com.dev6.domain.model.challenge.ChallengeEditReq
import com.dev6.domain.model.post.delete.PostEditReq
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.domain.repository.PostRepository
import com.dev6.domain.usecase.ChallengeEditBaseUseCase
import com.dev6.domain.usecase.PostDeleteBaseUseCase
import com.dev6.domain.usecase.PostEditBaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChallengeEditUseCase @Inject constructor(
    private val postRepository: PostRepository
) : ChallengeEditBaseUseCase {
    override suspend fun invoke(params: ChallengeEditReq) = flow {
        emit(UiState.Loding)
        runCatching {
            postRepository.editChallenge(params)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}