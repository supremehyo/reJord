package com.dev6.network

import com.dev6.model.challenge.ChallengeInfoResDTO
import com.dev6.model.nickname.NicknameExistCheckResDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BannerAPI {

    @Headers("Content-Type: application/json")
    @GET("/v1/challengeInfos")
    suspend fun getChallengeInfos()
    : Response<ChallengeInfoResDTO>


}