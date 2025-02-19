package com.dev6.repositoryImple

import com.dev6.domain.model.challenge.ChallengeEditReq
import com.dev6.domain.model.challenge.ChallengeEditRes
import com.dev6.domain.model.challenge.ChallengeRes
import com.dev6.domain.model.post.delete.PostEditReq
import com.dev6.domain.model.post.delete.PostEditRes
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.model.post.write.ChallengeWriteReq
import com.dev6.domain.model.post.write.ChallengeWriteRes
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.domain.model.post.write.PostWriteRes
import com.dev6.domain.repository.PostRepository
import com.dev6.mapper.toData
import com.dev6.mapper.toDomain
import com.dev6.remote.PostRemoteDataSource
import javax.inject.Inject

class PostRepositoryImple @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
) : PostRepository {
    override suspend fun getPostList(page: Int, postType : String, requestTime: String, size: Int): PostReadRes =
        postRemoteDataSource.getPostList(page, postType , requestTime, size).toDomain()

    override suspend fun postWrite(dto: PostWriteReq): PostWriteRes =
        postRemoteDataSource.postWrite(dto.toData()).toDomain()

    override suspend fun getChallengeList(page: Int, requestTime: String, size: Int): ChallengeRes =
        postRemoteDataSource.getChallengeList(page, requestTime, size).toDomain()

    override suspend fun postChallengeWrite(dto: ChallengeWriteReq): ChallengeWriteRes =
        postRemoteDataSource.postChallenge(dto.toData()).toDomain()

    override suspend fun getChallengeListWithUid(page: Int, size: Int): ChallengeRes =
        postRemoteDataSource.getChallengeListWithUid(page, size).toDomain()

    override suspend fun getPostListWithUid(page: Int, size: Int): PostReadRes =
        postRemoteDataSource.getPostListWithUid(page, size).toDomain()

    override suspend fun deletePost(postId: String): String =
        postRemoteDataSource.deletePost(postId)

    override suspend fun challengeDelete(challengeReviewId: String): String =
        postRemoteDataSource.challengeDelete(challengeReviewId)

    override suspend fun editPost(postEditReq: PostEditReq): PostEditRes =
        postRemoteDataSource.editPost(postEditReq.toData()).toDomain()

    override suspend fun editChallenge(editChallengeReq: ChallengeEditReq): ChallengeEditRes =
        postRemoteDataSource.editChallenge(editChallengeReq.toData()).toDomain()

}