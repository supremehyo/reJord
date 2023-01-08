package com.dev6.repositoryImple
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.domain.model.join.login.LoginRes
import com.dev6.domain.repository.LoginRepository
import com.dev6.mapper.toDomain
import com.dev6.mapper.toMapper
import com.dev6.remote.LoginRemoteDataSource
import javax.inject.Inject

class LoginRepositoryImple @Inject constructor(
    private val loginRemoteSource : LoginRemoteDataSource
) : LoginRepository {
    override suspend fun login(loginReq: LoginReq): LoginRes
    = loginRemoteSource.login(loginReq.toMapper()).toDomain()
}