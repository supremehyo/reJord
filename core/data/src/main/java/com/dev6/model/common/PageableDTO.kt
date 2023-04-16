package com.dev6.model.common
data class PageableDTO(
    val offset: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val sort: SortDTO,
    val unpaged: Boolean
)