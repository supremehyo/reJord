package com.dev6.repositoryImple
import com.dev6.domain.model.challenge.ChallengeInfoRes
import com.dev6.domain.repository.BannerRepository
import com.dev6.mapper.toDomain
import com.dev6.remote.BannerRemoteDataSource
import javax.inject.Inject

class BannerRepositoryImple @Inject constructor(
    private val bannerRemoteDataSource: BannerRemoteDataSource
) : BannerRepository{
    override suspend fun getChallengeInfos(): ChallengeInfoRes
    = bannerRemoteDataSource.getChallengeInfos().toDomain()
}