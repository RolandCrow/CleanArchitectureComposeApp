package com.example.data_source.local

import com.example.domain.Interaction
import kotlinx.coroutines.flow.Flow

interface LocalInteractionDataSource {
    fun getInteraction(): Flow<Interaction>
    suspend fun saveInteraction(interaction: Interaction)
}