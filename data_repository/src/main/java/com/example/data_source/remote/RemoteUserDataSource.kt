package com.example.data_source.remote

import com.example.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface RemoteUserDataSource {
    fun getUsers(): Flow<List<User>>
    fun getUser(id:Long): Flow<User>
}