package com.dev6.repositoryImple
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.repository.JoinRepository
import com.dev6.mapper.toDomain
import com.dev6.mapper.toMapper
import com.dev6.model.JoinReqDTO
import com.dev6.model.JoinResDTO
import com.dev6.remote.JoinRemoteDataSource
import javax.inject.Inject

class JoinRepositoryImple @Inject constructor(
    private val joinRemoteSource: JoinRemoteDataSource
    ) : JoinRepository {
    override suspend fun signUp(JoinReq: JoinReq): JoinRes
    = joinRemoteSource.signUp(JoinReq.toMapper()).toDomain()
}


