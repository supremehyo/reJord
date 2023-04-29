package com.dev6.domain.model.mypage

import com.dev6.domain.model.common.Pageable
import com.dev6.domain.model.common.Sort

data class FootPrintRes(
    val content : List<FootPrintResult>,
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
