package com.example.domain.repository

import com.example.domain.Interaction
import kotlinx.coroutines.flow.Flow

interface InteractionRepository {
    fun getInteraction(): Flow<Interaction>
    fun saveInteraction(interaction: Interaction): Flow<Interaction>
}