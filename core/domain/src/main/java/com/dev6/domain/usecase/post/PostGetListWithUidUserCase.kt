package com.dev6.domain.usecase.post

import com.dev6.common.uistate.UiState
import com.dev6.domain.repository.PostRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostGetListWithUidUserCase @Inject constructor(
    private val postRepository: PostRepository
){
    fun getPostListWithUid(page : Int , size : Int) = flow {
        emit(UiState.Loding)
        runCatching {
            postRepository.getPostListWithUid(page, size)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}