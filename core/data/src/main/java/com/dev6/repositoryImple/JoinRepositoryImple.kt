package com.dev6.repositoryImple
import com.dev6.domain.model.JoinReq
import com.dev6.domain.model.JoinRes
import com.dev6.domain.repository.JoinRepository
import com.dev6.model.JoinReqDTO
import com.dev6.remote.JoinRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JoinRepositoryImple @Inject constructor(
    private val joinRemoteSource: JoinRemoteDataSource
    ) : JoinRepository {
    override suspend fun signUp(joinReqDTO: JoinReq): JoinRes {
        return (joinRemoteSource.signUp(joinReqDTO as JoinReqDTO )) as JoinRes // 여기를 as 로 하지말고 mapper 로 교체
    }
}


