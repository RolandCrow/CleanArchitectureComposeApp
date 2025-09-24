package com.example.data_local.source

import com.example.data_local.db.post.PostDao
import com.example.data_local.db.post.PostEntity
import com.example.data_source.local.LocalPostDataSource
import com.example.domain.entity.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPostDataSourceImpl @Inject constructor(private val postDao: PostDao): LocalPostDataSource {
    override fun getPosts(): Flow<List<Post>>  = postDao.getPosts().map { posts ->
        posts.map {
            Post(it.id,it.userId,it.title,it.body)
        }
    }

    override suspend fun addPosts(post: List<Post>) = postDao.insertPosts(post.map {
        PostEntity(it.id, it.userId,it.title,it.body)
    })
}