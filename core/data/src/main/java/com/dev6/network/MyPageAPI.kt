package com.dev6.network
import com.dev6.model.mypage.MyDataResDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MyPageAPI {
    @Headers("Content-Type: application/json")
    @GET("/v1/users/mypage")
    suspend fun getMyData(): Response<MyDataResDTO>
}