package com.dev6.network
import com.dev6.model.login.LoginReqDTO
import com.dev6.model.login.LoginResDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginAPI {
    @Headers("Content-Type: application/json")
    @POST("/v1/login")
    suspend fun login(@Body loginReqDTO: LoginReqDTO): Response<LoginResDTO>


}