package com.example.data_source.remote

import com.example.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface RemotePostDataSource {
    fun getPosts(): Flow<List<Post>>
    fun getPost(id: Long): Flow<Post>
}