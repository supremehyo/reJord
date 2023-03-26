package com.dev6.domain.repository
import com.dev6.domain.model.challenge.ChallengeRes
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.model.post.write.ChallengeWriteReq
import com.dev6.domain.model.post.write.ChallengeWriteRes
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.domain.model.post.write.PostWriteRes

interface PostRepository {
    suspend fun getPostList(page: Int, requestTime: String, size: Int) : PostReadRes
    suspend fun postWrite(dto : PostWriteReq) : PostWriteRes
    suspend fun getChallengeList(page: Int, requestTime: String, size: Int) : ChallengeRes
    suspend fun postChallengeWrite(dto : ChallengeWriteReq) : ChallengeWriteRes
    suspend fun getChallengeListWithUid(page: Int, size: Int) : ChallengeRes
    suspend fun  getPostListWithUid(page: Int, size: Int) : PostReadRes
}