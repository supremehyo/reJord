package com.dev6.domain.repository

import com.dev6.domain.model.challenge.ChallengeInfoRes

interface BannerRepository {
    suspend fun getChallengeInfos() : ChallengeInfoRes
}