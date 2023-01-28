package com.dev6.domain.model.challenge
import com.dev6.domain.model.common.Pageable
import com.dev6.domain.model.common.Sort

data class ChallengeRes(
    val challengeReviewResult: List<ChallengeReviewResult>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageableDTO: Pageable,
    val size: Int,
    val sortDTO: Sort,
    val totalElements: Int,
    val totalPages: Int
)