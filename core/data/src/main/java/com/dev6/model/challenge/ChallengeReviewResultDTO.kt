package com.dev6.model.challenge

data class ChallengeReviewResultDTO(
    val challengeReviewId : String,
    val challengeReviewType : String,
    val contents : String,
    val createdDate : String,
    val nickname : String,
    val uid : String,
)