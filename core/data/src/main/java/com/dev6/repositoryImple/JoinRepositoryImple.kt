package com.dev6.repositoryImple
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.model.join.nickName.NicknameExistCheckRes
import com.dev6.domain.model.join.nickName.NicknameReq
import com.dev6.domain.model.join.nickName.NicknameUpdateRes
import com.dev6.domain.repository.JoinRepository
import com.dev6.mapper.toDomain
import com.dev6.mapper.toMapper
import com.dev6.remote.JoinRemoteDataSource
import javax.inject.Inject

class JoinRepositoryImple @Inject constructor(
    private val joinRemoteSource: JoinRemoteDataSource
    ) : JoinRepository {
    override suspend fun signUp(JoinReq: JoinReq): JoinRes
    = joinRemoteSource.signUp(JoinReq.toMapper()).toDomain()

    override suspend fun joinUpdate(nicknameReqDTO: NicknameReq): NicknameUpdateRes
    = joinRemoteSource.joinUpdate(nicknameReqDTO.toMapper()).toDomain()

    override suspend fun nicknameExistCheck(userId: String): NicknameExistCheckRes
    = joinRemoteSource.nicknameExistCheck(userId).toMapper()
}


