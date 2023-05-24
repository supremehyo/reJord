package com.dev6.domain.model.challenge

data class ChallengeEditRes(
    val challengeReviewId : String,
    val challengeReviewType : String,
    val contents : String,
    val createdDate : String,
    val nickname : String,
    val title : String,
    val uid : String
)
