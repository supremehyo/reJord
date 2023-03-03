package com.dev6.model.login
data class LoginResDTO(
    val nickname : String,
    val uid : String,
    val tokens : TokensDTO,
    val roles : List<String>,
    val userId : String
)
