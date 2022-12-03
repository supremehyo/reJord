package com.dev6.domain.repository
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes

interface JoinRepository {
    suspend fun signUp(joinReqDTO: JoinReq) : JoinRes
}