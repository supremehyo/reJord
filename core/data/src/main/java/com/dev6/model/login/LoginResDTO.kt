package com.dev6.model.login
import com.dev6.core.enums.UserType

data class LoginResDTO(
    val nickname : String,
    val uid : String,
    val userId : String,
    val userType : UserType,
)
