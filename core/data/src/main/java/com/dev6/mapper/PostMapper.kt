package com.dev6.mapper
import com.dev6.domain.model.common.Pageable
import com.dev6.domain.model.common.Sort
import com.dev6.domain.model.post.read.Content
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.model.common.PageableDTO
import com.dev6.model.common.SortDTO
import com.dev6.model.post.read.ContentDTO
import com.dev6.model.post.read.PostReadResDTO



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
    offset = offset,
    pageNumber = pageNumber,
    pageSize = pageSize,
    paged = paged,
    unpaged = unpaged,
    sort = sort.toDomain()
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
    offset = offset,
    pageNumber = pageNumber,
    pageSize = pageSize,
    paged = paged,
    unpaged = unpaged,
    sort = sort.toData()
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