package com.dev6.mapper
import com.dev6.domain.model.join.JoinReq
import com.dev6.domain.model.join.JoinRes
import com.dev6.domain.model.join.nickName.NicknameExistCheckRes
import com.dev6.domain.model.join.nickName.NicknameReq
import com.dev6.domain.model.join.nickName.NicknameUpdateRes
import com.dev6.core.enums.UserType
import com.dev6.model.JoinReqDTO
import com.dev6.model.JoinResDTO
import com.dev6.model.nickname.NicknameExistCheckResDTO
import com.dev6.model.nickname.NicknameReqDTO
import com.dev6.model.nickname.NicknameUpdateResDTO

internal fun JoinReq.toMapper() = JoinReqDTO(
    password = password ?: "",
    userId = userId ?:"",
    userType = userType?:""
)

internal fun JoinResDTO.toDomain() = JoinRes(
    nickname = this.nickname,
    errors = this.errors ?: emptyList(),
    uid = this.uid,
    userId = this.userId,
    userType = this.userType
)

internal fun NicknameReq.toMapper() = NicknameReqDTO(
    nickname = nickname?: ""
)

internal fun NicknameUpdateResDTO.toDomain() = NicknameUpdateRes(
    nickname = this.nickname,
    uid = this.uid,
    userId = this.userId,
    userType = this.userType
)

internal fun NicknameExistCheckResDTO.toMapper() = NicknameExistCheckRes(
    userId = userId ?: ""
)






