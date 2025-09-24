package com.example.data_source.local

import com.example.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface LocalPostDataSource {
    fun getPosts(): Flow<List<Post>>
    suspend fun addPosts(post: List<Post>)
}