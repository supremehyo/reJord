package com.dev6.remote
import com.dev6.model.executeNetworkHandling
import com.dev6.model.post.read.PostReadResDTO
import com.dev6.model.post.write.PostWriteReqDTO
import com.dev6.model.post.write.PostWriteResDTO
import com.dev6.network.PostAPI
import javax.inject.Inject

interface PostRemoteDataSource {
    suspend fun getPostList(
        page : Int,
        requestTime : String,
        size : Int
    ) : PostReadResDTO

    suspend fun postWrite(
        dto : PostWriteReqDTO
    ) : PostWriteResDTO
}

class PostRemoteDataSourceImpl @Inject constructor(
    private val postService : PostAPI
) : PostRemoteDataSource {
    override suspend fun getPostList(page: Int, requestTime: String, size: Int): PostReadResDTO {
        return postService.getPostList(page, requestTime, size).executeNetworkHandling()
    }

    override suspend fun postWrite(dto: PostWriteReqDTO) : PostWriteResDTO {
        return postService.postWrite(dto).executeNetworkHandling()
    }
}