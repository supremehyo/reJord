package com.dev6.domain.model.challenge

data class ChallengeReviewResult(
    val challengeReviewId : String,
    val challengeReviewType : String,
    val contents : String,
    val createdDate : List<Int>,
    val nickname : String,
    val uid : String,
)