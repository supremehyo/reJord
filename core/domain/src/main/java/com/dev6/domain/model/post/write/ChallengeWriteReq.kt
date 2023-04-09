package com.dev6.domain.model.post.write

data class ChallengeWriteReq(
    val challengeId : String,
    val challengeReviewType : String,
    val contents : String
)
