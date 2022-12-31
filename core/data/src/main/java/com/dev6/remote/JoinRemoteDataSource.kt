package com.dev6.remote
import com.dev6.model.JoinReqDTO
import com.dev6.model.JoinResDTO
import com.dev6.model.executeNetworkHandling
import com.dev6.network.JoinAPI
import javax.inject.Inject


interface JoinRemoteDataSource {
    suspend fun signUp(joinReqDTO: JoinReqDTO): JoinResDTO
}

class JoinRemoteDataSourceImpl @Inject constructor(
    private val joinService: JoinAPI
) : JoinRemoteDataSource {

    override suspend fun signUp(joinEntitiy: JoinReqDTO): JoinResDTO {
        return joinService.signUp(joinEntitiy).executeNetworkHandling()
    }
}