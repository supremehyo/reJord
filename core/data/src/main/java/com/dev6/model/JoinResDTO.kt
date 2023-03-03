package com.dev6.model

data class JoinResDTO(
    val errors: List<String>?,
    val nickname: String,
    val uid: String,
    val userId: String,
    val roles : List<String>
)
