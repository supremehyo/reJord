package com.dev6.mapper
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.domain.model.join.login.LoginRes
import com.dev6.model.login.LoginReqDTO
import com.dev6.model.login.LoginResDTO

internal fun LoginReq.toMapper() = LoginReqDTO(
    password = password ?: "",
    userId = userId ?:""
)

internal fun LoginResDTO.toDomain() = LoginRes(
    nickname = this.nickname,
    uid = this.uid,
    userId = this.userId,
    userType = this.userType
)
