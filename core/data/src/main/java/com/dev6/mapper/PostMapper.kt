package com.dev6.mapper
import com.dev6.domain.model.challenge.ChallengePage
import com.dev6.domain.model.challenge.ChallengeRes
import com.dev6.domain.model.challenge.ChallengeReviewResult
import com.dev6.domain.model.common.Pageable
import com.dev6.domain.model.common.Sort
import com.dev6.domain.model.post.read.Content
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.domain.model.post.write.ChallengeWriteReq
import com.dev6.domain.model.post.write.ChallengeWriteRes
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.domain.model.post.write.PostWriteRes
import com.dev6.model.challenge.ChallengePageDTO
import com.dev6.model.challenge.ChallengeResDTO
import com.dev6.model.challenge.ChallengeReviewResultDTO
import com.dev6.model.common.PageableDTO
import com.dev6.model.common.SortDTO
import com.dev6.model.post.read.ContentDTO
import com.dev6.model.post.read.PostReadResDTO
import com.dev6.model.post.write.ChallengeWriteReqDTO
import com.dev6.model.post.write.ChallengeWriteResDTO
import com.dev6.model.post.write.PostWriteReqDTO
import com.dev6.model.post.write.PostWriteResDTO


internal fun PostReadResDTO.toDomain() = PostReadRes(
    content = this?.content?.map { it.toDomain() } ?: listOf(),
    empty = empty,
    first = first,
    last = last ,
    number = number,
    numberOfElements = numberOfElements,
    size = size,
    pageable = pageable.toDomain(),
    sort = sort.toDomain(),
    totalElements = totalElements,
    totalPages = totalPages
)

internal fun PageableDTO.toDomain() = Pageable(
    page = page,
    size = size
)

internal fun SortDTO.toDomain()  = Sort(
    empty = empty,
    sorted =sorted,
    unsorted = unsorted
)

internal fun ContentDTO.toDomain() = Content(
    contents = contents,
    createdDate = createdDate,
    nickname = nickname,
    postId = postId,
    postType = postType,
    uid = uid
)



internal fun PostReadRes.toData() = PostReadResDTO(
    content = this?.content?.map { it.toData() } ?: listOf(),
    empty = empty,
    first = first,
    last = last ,
    number = number,
    numberOfElements = numberOfElements,
    size = size,
    pageable = pageable.toData(),
    sort = sort.toData(),
    totalElements = totalElements,
    totalPages = totalPages
)

internal fun Pageable.toData() = PageableDTO(
    page = page,
    size = size
)

internal fun Sort.toData()  = SortDTO(
    empty = empty,
    sorted =sorted,
    unsorted = unsorted
)

internal fun Content.toData() = ContentDTO(
    contents = contents,
    createdDate = createdDate,
    nickname = nickname,
    postId = postId,
    postType = postType,
    uid = uid
)

internal fun PostWriteReq.toData() = PostWriteReqDTO(
    contents = contents,
    postId = postId,
    postType = postType
)

internal fun PostWriteResDTO.toDomain() = PostWriteRes(
    contents = contents,
    postId = postId,
    postType = postType,
    uid = uid
)

internal fun ChallengeWriteReq.toData() = ChallengeWriteReqDTO(
    challengeId = challengeId,
    challengeReviewType = challengeReviewType,
    contents = contents
)

internal fun ChallengeWriteResDTO.toDomain() = ChallengeWriteRes(
    challengeReviewId = challengeReviewId,
    challengeReviewType = challengeReviewType,
    contents = contents,
    uid = uid
)


internal fun ChallengeReviewResultDTO.toDomain() = ChallengeReviewResult(
    challengeReviewId = challengeReviewId,
    challengeReviewType = challengeReviewType,
    contents = contents,
    createdDate = createdDate,
    nickname = nickname,
    title = title,
    uid = uid
)

internal fun ChallengePageDTO.toDomain() = ChallengePage(
    page = page,
    size = size
)

internal fun ChallengeResDTO.toDomain() = ChallengeRes(
    content = this?.content?.map { it.toDomain() } ?: listOf(),
    empty = empty,
    first = first,
    last = last,
    number = number,
    numberOfElements = numberOfElements,
    pageable = pageable.toDomain(),
    size =size,
    sort = sort.toDomain(),
    totalElements = totalElements,
    totalPages = totalPages
)



