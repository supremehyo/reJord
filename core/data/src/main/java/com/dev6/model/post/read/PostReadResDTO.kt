package com.dev6.model.post.read
import com.dev6.model.common.PageableDTO
import com.dev6.model.common.SortDTO

data class PostReadResDTO(
    val content: List<ContentDTO>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: PageableDTO,
    val size: Int,
    val sort: SortDTO,
    val totalElements: Int,
    val totalPages: Int
)