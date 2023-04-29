package com.dev6.remote

import com.dev6.model.executeNetworkHandling
import com.dev6.model.mypage.BadgeByUidResultDTO
import com.dev6.model.mypage.FootPrintResDTO
import com.dev6.model.mypage.MyDataResDTO
import com.dev6.network.MyPageAPI
import javax.inject.Inject

interface MyPageRemoteDataSource {
    suspend fun getMyPage() : MyDataResDTO
    suspend fun getFootPrintList(page:Int,size:Int) : FootPrintResDTO
    suspend fun getBadgeInfoList() : List<BadgeByUidResultDTO>
}

class MyPageRemoteDataSourceImpl @Inject constructor(
    private val myPageAPI: MyPageAPI
) : MyPageRemoteDataSource {
    override suspend fun getMyPage(): MyDataResDTO {
       return myPageAPI.getMyData().executeNetworkHandling()
    }

    override suspend fun getFootPrintList(page:Int,size:Int): FootPrintResDTO {
        return myPageAPI.getFootPrintList(page, size).executeNetworkHandling()
    }

    override suspend fun getBadgeInfoList(): List<BadgeByUidResultDTO> {
        return myPageAPI.getBadgeInfoList().executeNetworkHandling()
    }
}