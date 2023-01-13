package com.dev6.domain.model.join.nickName
import com.dev6.core.enums.UserType


data class NicknameUpdateRes(
    val nickname : String,
    val uid : String,
    val userId : String,
    val userType : UserType
)
