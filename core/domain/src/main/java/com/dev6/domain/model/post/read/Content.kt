package com.dev6.domain.model.post.read

data class Content(
    val contents: String,
    val createdDate: List<Int>,
    val nickname: String,
    val postId: String,
    val postType: String,
    val uid: String
)