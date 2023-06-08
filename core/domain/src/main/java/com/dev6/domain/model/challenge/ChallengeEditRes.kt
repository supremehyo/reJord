package com.dev6.domain.model.challenge

data class ChallengeEditRes(
    val challengeReviewId : String,
    val challengeReviewType : String,
    val contents : String,
    val createdDate : List<String>,
    val nickname : String,
    val title : String,
    val uid : String
)
