package com.dev6.domain.usecase.write
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.post.write.ChallengeWriteReq
import com.dev6.domain.repository.PostRepository
import com.dev6.domain.usecase.ChallengeWriteBaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChallengeWriteUseCase @Inject constructor(
    private val postRepository: PostRepository
) : ChallengeWriteBaseUseCase {
    override suspend fun invoke(params: ChallengeWriteReq)= flow {
        emit(UiState.Loding)
        runCatching {
            postRepository.postChallengeWrite(params)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

}