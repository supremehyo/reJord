package com.dev6.domain.model.join

data class JoinReq(
    var password : String,
    var userId : String,
    var userType : String
) : java.io.Serializable
