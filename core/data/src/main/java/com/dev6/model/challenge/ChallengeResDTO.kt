package com.dev6.model.challenge
import com.dev6.model.common.SortDTO

data class ChallengeResDTO(
    val content : List<ChallengeReviewResultDTO>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: ChallengePageDTO,
    val size: Int,
    val sort: SortDTO,
    val totalElements: Int,
    val totalPages: Int
)