package com.dev6.repositoryImple
import com.dev6.domain.model.challenge.ChallengeRes
import com.dev6.domain.model.post.read.PostReadRes
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
    override suspend fun getPostList(page: Int, requestTime: String, size: Int): PostReadRes
    = postRemoteDataSource.getPostList(page, requestTime, size).toDomain()

    override suspend fun postWrite(dto: PostWriteReq): PostWriteRes
    = postRemoteDataSource.postWrite(dto.toData()).toDomain()

    override suspend fun getChallengeList(page: Int, requestTime: String, size: Int): ChallengeRes
    = postRemoteDataSource.getChallengeList(page, requestTime, size).toDomain()

}