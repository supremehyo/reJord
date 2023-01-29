package com.dev6.network
import com.dev6.model.post.read.PostReadResDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PostAPI {
    @Headers("Content-Type: application/json")
    @GET("/v1/postInfos")
    suspend fun getPostList(
        @Query("page") page:Int,
        @Query("requestTime") requestTime:String,
        @Query("size") size:Int,
    ): Response<PostReadResDTO>
}