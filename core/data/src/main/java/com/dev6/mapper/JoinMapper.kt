package com.dev6.mapper
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.model.JoinReqDTO
import com.dev6.model.JoinResDTO

internal fun JoinReq.toMapper() = JoinReqDTO(
    nickname = nickname,
    password = password,
    userId = userId,
    userType = userType
)

internal fun JoinReqDTO.toDomain() = JoinReq(
    nickname = nickname,
    password = password,
    userId = userId,
    userType = userType
)

internal fun JoinRes.toMapper() = JoinResDTO(
    nickname = nickname,
    errors = errors,
    uid = uid,
    userId = userId,
    userType = userType
)

internal fun JoinResDTO.toDomain() = JoinRes(
    nickname = nickname,
    errors = errors,
    uid = uid,
    userId = userId,
    userType = userType
)

