package com.dev6.network
import com.dev6.model.challenge.ChallengeResDTO
import com.dev6.model.login.LoginReqDTO
import com.dev6.model.login.LoginResDTO
import com.dev6.model.post.patch.ChallengePatchReqDTO
import com.dev6.model.post.patch.ChallengePatchResDTO
import com.dev6.model.post.patch.PostPatchReqDTO
import com.dev6.model.post.patch.PostPatchResDTO
import com.dev6.model.post.read.PostReadResDTO
import com.dev6.model.post.write.ChallengeWriteReqDTO
import com.dev6.model.post.write.ChallengeWriteResDTO
import com.dev6.model.post.write.PostWriteReqDTO
import com.dev6.model.post.write.PostWriteResDTO
import retrofit2.Response
import retrofit2.http.*

interface PostAPI {
    @Headers("Content-Type: application/json")
    @GET("/v1/postInfos")
    suspend fun getPostList(
        @Query("page") page:Int,
        @Query("postType") postType : String,
        @Query("requestTime") requestTime:String,
        @Query("size") size:Int,
    ): Response<PostReadResDTO>


    @Headers("Content-Type: application/json")
    @GET("/v1/challengeReviewInfos")
    suspend fun getChallengeList(
        @Query("page") page:Int,
        @Query("requestTime") requestTime:String,
        @Query("size") size:Int,
    ): Response<ChallengeResDTO>

    @Headers("Content-Type: application/json")
    @POST("/v1/posts")
    suspend fun postWrite(@Body postWriteReqDTO: PostWriteReqDTO): Response<PostWriteResDTO>

    @Headers("Content-Type: application/json")
    @POST("/v1/challengeReviews")
    suspend fun challengeWrite(@Body challengeWriteReqDTO: ChallengeWriteReqDTO): Response<ChallengeWriteResDTO>

    @Headers("Content-Type: application/json")
    @GET("/v1/challengeReviewInfos/withUid")
    suspend fun getChallengeListWithUid(
        @Query("page") page:Int,
        @Query("size") size:Int
    ): Response<ChallengeResDTO>

    @Headers("Content-Type: application/json")
    @GET("/v1/postInfos/withUid")
    suspend fun getPostListWithUid(
        @Query("page") page:Int,
        @Query("size") size:Int
    ): Response<PostReadResDTO>

    //게시물 삭제
    @Headers("Content-Type: application/json")
    @DELETE("/v1/posts/{postId}")
    suspend fun postDelete(
        @Path("postId") postId : String
    ): Response<String>

    //챌린지 리뷰 게시글 수정
    @Headers("Content-Type: application/json")
    @PATCH("/v1/challengeReviewInfos/withChallengeReviewId")
    suspend fun challengePatch(
        @Body challengePatchReqDTO: ChallengePatchReqDTO
    ): Response<ChallengePatchResDTO>

    //일반 게시글 수정
    @Headers("Content-Type: application/json")
    @PATCH("/v1/challengeReviewInfos/withChallengeReviewId")
    suspend fun PostPatch(
        @Body postPatchReqDTO: PostPatchReqDTO
    ): Response<PostPatchResDTO>

}