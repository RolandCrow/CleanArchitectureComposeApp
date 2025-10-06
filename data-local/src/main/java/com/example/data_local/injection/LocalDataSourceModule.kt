package com.example.data_local.injection

import com.example.data_local.source.LocalInteractionDataSourceImpl
import com.example.data_local.source.LocalPostDataSourceImpl
import com.example.data_local.source.LocalUserDataSourceImpl
import com.example.data_source.local.LocalInteractionDataSource
import com.example.data_source.local.LocalPostDataSource
import com.example.data_source.local.LocalUserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindPostDataSource(localDataSourceImpl: LocalPostDataSourceImpl): LocalPostDataSource

    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: LocalUserDataSourceImpl): LocalUserDataSource

    @Binds
    abstract fun bindInteraction(interactionDataSource: LocalInteractionDataSourceImpl): LocalInteractionDataSource
}