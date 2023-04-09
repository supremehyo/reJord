package com.dev6.remote

import com.dev6.model.executeNetworkHandling
import com.dev6.model.mypage.MyDataResDTO
import com.dev6.network.MyPageAPI
import javax.inject.Inject

interface MyPageRemoteDataSource {
    suspend fun getMyPage() : MyDataResDTO
}

class MyPageRemoteDataSourceImpl @Inject constructor(
    private val myPageAPI: MyPageAPI
) : MyPageRemoteDataSource {
    override suspend fun getMyPage(): MyDataResDTO {
       return myPageAPI.getMyData().executeNetworkHandling()
    }
}