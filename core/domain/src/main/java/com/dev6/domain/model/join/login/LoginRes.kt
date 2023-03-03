package com.dev6.domain.model.join.login
data class LoginRes(
    val nickname : String,
    val roles : List<String>,
    val tokens: Tokens,
    val uid : String,
    val userId : String
)
