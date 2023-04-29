package com.dev6.repositoryImple

import com.dev6.domain.model.mypage.BadgeByUidResult
import com.dev6.domain.model.mypage.FootPrintRes
import com.dev6.domain.model.mypage.MyData
import com.dev6.domain.repository.MyPageRepository
import com.dev6.mapper.toDomain
import com.dev6.model.mypage.BadgeByUidResultDTO
import com.dev6.remote.MyPageRemoteDataSource
import javax.inject.Inject

class MyPageRepositroyImple @Inject constructor(
    private val myPageRemoteDataSource: MyPageRemoteDataSource
): MyPageRepository {
    override suspend fun getMyData(): MyData =
        myPageRemoteDataSource.getMyPage().toDomain()

    override suspend fun getFootPrintList(page:Int,size:Int): FootPrintRes =
        myPageRemoteDataSource.getFootPrintList(page,size).toDomain()

    override suspend fun getBadgeInfoList(): List<BadgeByUidResult> =
        myPageRemoteDataSource.getBadgeInfoList().map { it.toDomain() }
}