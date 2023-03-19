package com.dev6.remote

import com.dev6.model.challenge.ChallengeInfoResDTO
import com.dev6.model.executeNetworkHandling
import com.dev6.network.BannerAPI
import javax.inject.Inject

interface BannerRemoteDataSource{
    suspend fun getChallengeInfos() : ChallengeInfoResDTO
}

class BannerRemoteDataSourceImpl @Inject constructor(
    private val bannerAPI: BannerAPI
) : BannerRemoteDataSource {

    override suspend fun getChallengeInfos(): ChallengeInfoResDTO {
        return bannerAPI.getChallengeInfos().executeNetworkHandling()
    }

}