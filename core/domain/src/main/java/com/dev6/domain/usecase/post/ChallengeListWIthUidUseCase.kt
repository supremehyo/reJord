package com.dev6.domain.usecase.post
import com.dev6.common.uistate.UiState
import com.dev6.domain.repository.PostRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChallengeListWIthUidUseCase @Inject constructor(
    private val postRepository: PostRepository
){
     fun getChallengeListWithUid(page : Int , size : Int) = flow {
        emit(UiState.Loding)
        runCatching {
            postRepository.getChallengeListWithUid(page, size)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}