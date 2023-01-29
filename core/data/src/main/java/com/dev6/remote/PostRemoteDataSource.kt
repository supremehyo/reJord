package com.dev6.remote
import android.util.Log
import com.dev6.model.executeNetworkHandling
import com.dev6.model.post.read.PostReadResDTO
import com.dev6.network.PostAPI
import javax.inject.Inject

interface PostRemoteDataSource {
    suspend fun getPostList(
        page : Int,
        requestTime : String,
        size : Int
    ) : PostReadResDTO
}

class PostRemoteDataSourceImpl @Inject constructor(
    private val postService : PostAPI
) : PostRemoteDataSource {
    override suspend fun getPostList(page: Int, requestTime: String, size: Int): PostReadResDTO {
        Log.v("test" , "post list request")
        return postService.getPostList(page, requestTime, size).executeNetworkHandling()
    }
}