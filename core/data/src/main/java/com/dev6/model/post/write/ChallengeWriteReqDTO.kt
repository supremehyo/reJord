package com.dev6.model.post.write

data class ChallengeWriteReqDTO(
    val challengeId : String,
    val challengeReviewType : String,
    val contents : String
)