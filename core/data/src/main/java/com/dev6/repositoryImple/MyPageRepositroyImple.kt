package com.dev6.repositoryImple

import com.dev6.domain.model.mypage.MyData
import com.dev6.domain.repository.MyPageRepository
import com.dev6.mapper.toDomain
import com.dev6.remote.MyPageRemoteDataSource
import javax.inject.Inject

class MyPageRepositroyImple @Inject constructor(
    private val myPageRemoteDataSource: MyPageRemoteDataSource
): MyPageRepository {
    override suspend fun getMyData(): MyData =
        myPageRemoteDataSource.getMyPage().toDomain()
}