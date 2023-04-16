package com.dev6.mapper
import com.dev6.domain.model.join.login.LoginReq
import com.dev6.domain.model.join.login.LoginRes
import com.dev6.domain.model.join.login.Tokens
import com.dev6.model.login.LoginReqDTO
import com.dev6.model.login.LoginResDTO
import com.dev6.model.login.TokensDTO

internal fun LoginReq.toMapper() = LoginReqDTO(
    password = password ?: "",
    userId = userId ?:""
)

internal fun LoginResDTO.toDomain() = LoginRes(
    nickname = this.nickname,
    uid = this.uid,
    userId = this.userId,
    roles = this.roles,
    tokens = this.tokens.toDomain()
)

internal fun TokensDTO.toDomain() = Tokens(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken
)
