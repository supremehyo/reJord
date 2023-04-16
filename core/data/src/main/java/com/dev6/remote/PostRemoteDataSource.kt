package com.dev6.remote
import com.dev6.domain.model.post.write.ChallengeWriteReq
import com.dev6.domain.model.post.write.ChallengeWriteRes
import com.dev6.model.challenge.ChallengeResDTO
import com.dev6.model.challenge.ChallengeReviewResultDTO
import com.dev6.model.executeNetworkHandling
import com.dev6.model.post.read.PostReadResDTO
import com.dev6.model.post.write.ChallengeWriteReqDTO
import com.dev6.model.post.write.ChallengeWriteResDTO
import com.dev6.model.post.write.PostWriteReqDTO
import com.dev6.model.post.write.PostWriteResDTO
import com.dev6.network.PostAPI
import javax.inject.Inject

interface PostRemoteDataSource {
    suspend fun getPostList(
        page : Int,
        postType : String,
        requestTime : String,
        size : Int
    ) : PostReadResDTO

    suspend fun postWrite(
        dto : PostWriteReqDTO
    ) : PostWriteResDTO

    suspend fun getChallengeList(
        page : Int,
        requestTime : String,
        size : Int
    ) : ChallengeResDTO

    suspend fun postChallenge(
        dto : ChallengeWriteReqDTO
    ) : ChallengeWriteResDTO

    suspend fun getChallengeListWithUid(
        page: Int,
        size: Int
    ) : ChallengeResDTO
    suspend fun  getPostListWithUid(
        page: Int,
        size: Int
    ) : PostReadResDTO
}

class PostRemoteDataSourceImpl @Inject constructor(
    private val postService : PostAPI
) : PostRemoteDataSource {
    override suspend fun getPostList(page: Int, postType : String, requestTime: String, size: Int): PostReadResDTO {
        return postService.getPostList(page, postType , requestTime, size).executeNetworkHandling()
    }

    override suspend fun postWrite(dto: PostWriteReqDTO) : PostWriteResDTO {
        return postService.postWrite(dto).executeNetworkHandling()
    }

    override suspend fun getChallengeList(
        page: Int,
        requestTime: String,
        size: Int
    ): ChallengeResDTO {
        return postService.getChallengeList(page, requestTime, size).executeNetworkHandling()
    }

    override suspend fun postChallenge(dto: ChallengeWriteReqDTO): ChallengeWriteResDTO {
        return postService.challengeWrite(dto).executeNetworkHandling()
    }

    override suspend fun getChallengeListWithUid(page: Int, size: Int) : ChallengeResDTO {
        return postService.getChallengeListWithUid(page, size).executeNetworkHandling()
    }

    override suspend fun getPostListWithUid(page: Int, size: Int): PostReadResDTO {
        return postService.getPostListWithUid(page, size).executeNetworkHandling()
    }
}