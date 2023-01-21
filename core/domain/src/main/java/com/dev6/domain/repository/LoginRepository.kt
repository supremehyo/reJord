package com.dev6.domain.repository
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.domain.model.join.login.LoginRes

interface LoginRepository {
    suspend fun login(loginReq: LoginReq) : LoginRes
}