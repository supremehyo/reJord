package com.dev6.domain.model.post.write

data class PostWriteReq(
    val contents : String,
    val postId : String,
    val postType : String,
)
