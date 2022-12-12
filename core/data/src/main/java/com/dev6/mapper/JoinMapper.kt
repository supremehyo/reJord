package com.dev6.mapper
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.model.JoinReqDTO
import com.dev6.model.JoinResDTO

internal fun JoinReq.toMapper() = JoinReqDTO(
    password = password ?: "",
    userId = userId ?:"",
    userType = userType?:""
)

internal fun JoinReqDTO.toDomain() = JoinReq(
    password = this.password,
    userId = this.userId,
    userType = this.userType
)

internal fun JoinRes.toMapper() = JoinResDTO(
    nickname = nickname,
    errors = errors ?: emptyList(),
    uid = uid,
    userId = userId,
    userType = userType
)

internal fun JoinResDTO.toDomain() = JoinRes(
    nickname = this.nickname,
    errors = this.errors ?: emptyList(),
    uid = this.uid,
    userId = this.userId,
    userType = this.userType
)

