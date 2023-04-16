package com.dev6.domain.model.challenge
import com.dev6.domain.model.common.Pageable
import com.dev6.domain.model.common.Sort

data class ChallengeRes(
    val content: List<ChallengeReviewResult>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: ChallengePage,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
)