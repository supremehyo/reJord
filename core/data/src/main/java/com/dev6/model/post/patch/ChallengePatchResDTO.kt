package com.dev6.model.post.patch

data class ChallengePatchResDTO(
    val challengeReviewId : String,
    val challengeReviewType : String,
    val contents : String,
    val createdDate : String,
    val nickname : String,
    val title : String,
    val uid : String
)
