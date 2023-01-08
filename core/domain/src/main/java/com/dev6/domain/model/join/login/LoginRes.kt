package com.dev6.domain.model.join.login

import com.dev6.core.enums.UserType

data class LoginRes(
    val nickname : String,
    val uid : String,
    val userId : String,
    val userType : UserType,
)
