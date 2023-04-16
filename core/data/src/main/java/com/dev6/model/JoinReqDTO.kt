package com.dev6.model

data class JoinReqDTO(
    val password : String,
    val userId : String,
    val roles : List<String>
)
