package com.dev6.model.challenge
import com.dev6.model.common.PageableDTO
import com.dev6.model.common.SortDTO

data class ChallengeResDTO(
    val challengeReviewResultDTO: List<ChallengeReviewResultDTO>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageableDTO: PageableDTO,
    val size: Int,
    val sortDTO: SortDTO,
    val totalElements: Int,
    val totalPages: Int
)