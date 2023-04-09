package com.dev6.mapper

import com.dev6.domain.model.mypage.MyData
import com.dev6.model.mypage.MyDataResDTO

internal fun MyDataResDTO.toDomain() = MyData(
    badgeAmount =badgeAmount,
    dday =dday,
    nickname=nickname,
    totalFootprintAmount=totalFootprintAmount
)