package com.dev6.domain.usecase
import android.util.Log
import com.dev6.common.uistate.UiState
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.domain.repository.LoginRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository : LoginRepository
) : LoginReposBaseUseCase  {
    override suspend fun invoke(loginReq: LoginReq) = flow {
        emit(UiState.Loding)
        runCatching {
            loginRepository.login(loginReq)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            Log.v("test" , it.message.toString())
            emit(UiState.Error(it))
        }
    }
}