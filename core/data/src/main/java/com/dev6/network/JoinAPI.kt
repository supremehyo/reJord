package com.dev6.network
import com.dev6.model.JoinReqDTO
import com.dev6.model.JoinResDTO
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface JoinAPI {

    @Headers("Content-Type: application/json")
    @POST("/v1/users")
    suspend fun signUp(@Body joinReqDTO: JoinReqDTO): JoinResDTO

}