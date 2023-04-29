package com.dev6.network
import com.dev6.model.mypage.BadgeByUidResultDTO
import com.dev6.model.mypage.FootPrintResDTO
import com.dev6.model.mypage.MyDataResDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MyPageAPI {
    @Headers("Content-Type: application/json")
    @GET("/v1/users/mypage")
    suspend fun getMyData(): Response<MyDataResDTO>

    @Headers("Content-Type: application/json")
    @GET("/v1/footprintInfos/withUid")
    suspend fun getFootPrintList(
        @Query("page") page:Int,
        @Query("size") size:Int
    ): Response<FootPrintResDTO>

    @Headers("Content-Type: application/json")
    @GET("/v1/badgeInfos/withUid")
    suspend fun getBadgeInfoList(): Response<List<BadgeByUidResultDTO>>

}