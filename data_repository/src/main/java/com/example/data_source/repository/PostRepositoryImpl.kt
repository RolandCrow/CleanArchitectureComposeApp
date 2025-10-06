package com.example.data_source.repository

import com.example.data_source.local.LocalPostDataSource
import com.example.data_source.remote.RemotePostDataSource
import com.example.domain.entity.Post
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class PostRepositoryImpl(
    private val remotePostDataSource: RemotePostDataSource,
    private val localPostDataSource: LocalPostDataSource
): PostRepository {
    override fun getPosts(): Flow<List<Post>> = remotePostDataSource.getPosts()
        .onEach {
            localPostDataSource.addPosts(it)
        }

    override fun getPost(id: Long): Flow<Post> = remotePostDataSource.getPost(id = id)
        .onEach {
            localPostDataSource.addPosts(listOf(it))
        }
}