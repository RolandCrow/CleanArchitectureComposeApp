package com.example.data_source.repository

import com.example.data_source.local.LocalUserDataSource
import com.example.data_source.remote.RemoteUserDataSource
import com.example.domain.entity.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class UserRepositoryImpl(
    private val remoteUserDataSource: RemoteUserDataSource,
    private val localUserDataSource: LocalUserDataSource
): UserRepository {
    override fun getUsers(): Flow<List<User>> = remoteUserDataSource.getUsers()
        .onEach {
            localUserDataSource.addUsers(it)
        }

    override fun getUser(id: Long): Flow<User> = remoteUserDataSource.getUser(id)
        .onEach {
            localUserDataSource.addUsers(listOf(it))
        }

}