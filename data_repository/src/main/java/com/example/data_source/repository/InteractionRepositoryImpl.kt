package com.example.data_source.repository

import com.example.data_source.local.LocalInteractionDataSource
import com.example.domain.Interaction
import com.example.domain.repository.InteractionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class InteractionRepositoryImpl(
  private val interactionDataSource: LocalInteractionDataSource
): InteractionRepository {
    override fun getInteraction(): Flow<Interaction> = interactionDataSource.getInteraction()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun saveInteraction(interaction: Interaction): Flow<Interaction> = flow {
        interactionDataSource.saveInteraction(interaction)
        this.emit(Unit)
    }.flatMapLatest {
        getInteraction()
    }

}