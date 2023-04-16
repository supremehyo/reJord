package com.dev6.domain.usecase.write
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.domain.repository.PostRepository
import com.dev6.domain.usecase.PostWriteBaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WriteUseCase @Inject constructor(
    private val postRepository: PostRepository
) : PostWriteBaseUseCase {
    override suspend fun invoke(params: PostWriteReq) = flow {
        emit(UiState.Loding)
        runCatching {
            postRepository.postWrite(params)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}