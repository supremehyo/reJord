package com.dev6.network
import com.dev6.model.login.LoginReqDTO
import com.dev6.model.login.LoginResDTO
import com.dev6.model.login.TokensDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface LoginAPI {
    @Headers("Content-Type: application/json")
    @POST("/v1/login")
    suspend fun login(@Body loginReqDTO: LoginReqDTO): Response<LoginResDTO>

    @Headers("Content-Type: application/json")
    @GET("/v1/token/refresh")
    suspend fun getToken(
        @Header("Authorization") authorization : String?
    ): TokensDTO

}