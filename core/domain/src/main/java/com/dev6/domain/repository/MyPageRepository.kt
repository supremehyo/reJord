package com.dev6.domain.repository

import com.dev6.domain.model.mypage.BadgeByUidResult
import com.dev6.domain.model.mypage.FootPrintRes
import com.dev6.domain.model.mypage.MyData

interface MyPageRepository {
    suspend fun getMyData() : MyData
    suspend fun getFootPrintList(page:Int,size:Int) : FootPrintRes
    suspend fun getBadgeInfoList() : List<BadgeByUidResult>
}