package com.dev6.domain.model.post.read
import com.dev6.domain.model.common.Pageable
import com.dev6.domain.model.common.Sort

data class PostReadRes(
    val content: List<Content>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
)