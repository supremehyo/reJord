package com.dev6.model.post.patch

data class PostPatchResDTO(
    val contents : String,
    val createdDate : List<String>,
    val nickname : String,
    val postId : String,
    val postType : String,
    val uid : String
)
