package com.dev6.domain.repository

import com.dev6.domain.model.mypage.MyData

interface MyPageRepository {
    suspend fun getMyData() : MyData
}