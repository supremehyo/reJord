package com.dev6.domain.usecase.post
import android.util.Log
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.post.read.PostReadReq
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.repository.PostRepository
import com.dev6.domain.usecase.PostGetListReposBaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostGetListUserCase @Inject constructor(
    private val postRepository: PostRepository
) : PostGetListReposBaseUseCase {
    override suspend fun invoke(params: PostReadReq) = flow {
        emit(UiState.Loding)
        runCatching {
            postRepository.getPostList(params.page,params.requestTime,params.size)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}