package com.dev6.domain.model.post.delete

data class PostEditRes(
    val contents : String,
    val createdDate : List<String>,
    val nickname : String,
    val postId : String,
    val postType : String,
    val uid : String
)
