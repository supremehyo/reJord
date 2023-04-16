package com.dev6.model.challenge

data class ChallengeReviewResultDTO(
    val challengeReviewId : String,
    val challengeReviewType : String,
    val contents : String,
    val createdDate : List<Int>,
    val nickname : String,
    val title : String ,
    val uid : String,
)