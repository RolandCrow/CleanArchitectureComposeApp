package com.example.cleanarchitecturecomposeapp.inject

import com.example.data_source.local.LocalInteractionDataSource
import com.example.data_source.local.LocalPostDataSource
import com.example.data_source.local.LocalUserDataSource
import com.example.data_source.remote.RemotePostDataSource
import com.example.data_source.remote.RemoteUserDataSource
import com.example.data_source.repository.InteractionRepositoryImpl
import com.example.data_source.repository.PostRepositoryImpl
import com.example.data_source.repository.UserRepositoryImpl
import com.example.domain.repository.InteractionRepository
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun providePostRepository(
        remotePostDataSource: RemotePostDataSource,
        localPostDataSource: LocalPostDataSource
    ): PostRepository = PostRepositoryImpl(
        remotePostDataSource,
        localPostDataSource
    )

    @Provides
    fun providesUserRepository(
        remoteUserDataSource: RemoteUserDataSource,
        localUserDataSource: LocalUserDataSource
    ): UserRepository = UserRepositoryImpl(
        remoteUserDataSource,
        localUserDataSource
    )

    @Provides
    fun provideInteractionRepository(
        interactionDataSource: LocalInteractionDataSource
    ): InteractionRepository = InteractionRepositoryImpl(
        interactionDataSource
    )
}