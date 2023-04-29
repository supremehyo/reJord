package com.dev6.mapper

import com.dev6.domain.model.mypage.BadgeByUidResult
import com.dev6.domain.model.mypage.FootPrintRes
import com.dev6.domain.model.mypage.FootPrintResult
import com.dev6.domain.model.mypage.MyData
import com.dev6.model.mypage.BadgeByUidResultDTO
import com.dev6.model.mypage.FootPrintResDTO
import com.dev6.model.mypage.FootPrintResultDTO
import com.dev6.model.mypage.MyDataResDTO

internal fun MyDataResDTO.toDomain() = MyData(
    badgeAmount =badgeAmount,
    dday =dday,
    nickname=nickname,
    totalFootprintAmount=totalFootprintAmount
)

internal fun FootPrintResDTO.toDomain() = FootPrintRes(
    content = this?.content?.map { it.toDomain() } ?: listOf(),
    empty= empty,
    first = first,
    last = last,
    number = number,
    numberOfElements = numberOfElements,
    pageable = pageable.toDomain(),
    size = size,
    sort = sort.toDomain(),
    totalElements = totalElements,
    totalPages = totalPages
)

internal fun FootPrintResultDTO.toDomain() = FootPrintResult(
    badgeCode = badgeCode,
    createdDate = createdDate,
    footprintAcquirementType = footprintAcquirementType,
    footprintAmount = footprintAmount,
    footprintId = footprintId,
    parentId = parentId,
    title = title
)

internal fun BadgeByUidResultDTO.toDomain() = BadgeByUidResult(
    badgeCode = badgeCode,
    badgeName = badgeName,
    imageUrl = imageUrl
)