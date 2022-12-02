package com.dev6.domain.repository
import com.dev6.domain.model.JoinReq
import com.dev6.domain.model.JoinRes

interface JoinRepository {
    suspend fun signUp(joinReqDTO: JoinReq) : JoinRes
}