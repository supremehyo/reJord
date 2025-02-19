package com.dev6.model.nickname
import com.dev6.core.enums.UserType

data class NicknameUpdateResDTO(
    val nickname : String,
    val uid : String,
    val userId : String,
    val roles : List<String>
)
