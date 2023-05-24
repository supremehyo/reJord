package com.dev6.domain.usecase.write

import com.dev6.common.uistate.UiState
import com.dev6.domain.model.post.delete.PostEditReq
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.domain.repository.PostRepository
import com.dev6.domain.usecase.PostDeleteBaseUseCase
import com.dev6.domain.usecase.PostEditBaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostEditUseCase @Inject constructor(
    private val postRepository: PostRepository
) : PostEditBaseUseCase {
    override suspend fun invoke(params: PostEditReq) = flow {
        emit(UiState.Loding)
        runCatching {
            postRepository.editPost(params)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}