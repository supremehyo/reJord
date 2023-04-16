package com.dev6.model.post.write

data class PostWriteReqDTO(
    val contents : String,
    val postId : String,
    val postType : String,
)
