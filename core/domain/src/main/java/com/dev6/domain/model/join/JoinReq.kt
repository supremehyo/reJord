package com.dev6.domain.model.join

data class JoinReq(
    val password : String,
    val userId : String,
    val roles : List<String>
) : java.io.Serializable
