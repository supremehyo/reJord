package com.dev6.domain.repository

import com.dev6.domain.model.post.read.PostReadRes

interface PostRepository {
    suspend fun getPostList(page: Int, requestTime: String, size: Int) : PostReadRes
}