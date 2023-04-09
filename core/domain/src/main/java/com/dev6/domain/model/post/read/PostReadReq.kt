package com.dev6.domain.model.post.read

data class PostReadReq(
    val page : Int,
    val postType : String,
    val requestTime : String,
    val size : Int
)
