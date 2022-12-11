package com.dev6.domain.model.join

data class JoinRes(
    val errors: List<String>,
    val nickname: String,
    val uid: String,
    val userId: String,
    val userType: String
)
