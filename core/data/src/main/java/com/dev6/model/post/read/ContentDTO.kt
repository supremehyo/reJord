package com.dev6.model.post.read

data class ContentDTO(
    val contents: String,
    val createdDate: List<Int>,
    val nickname: String,
    val postId: String,
    val postType: String,
    val uid: String
)