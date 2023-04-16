package com.dev6.domain.model.post.write

data class PostWriteRes(
    val contents : String,
    val postId : String,
    val postType : String,
    val uid : String
)
