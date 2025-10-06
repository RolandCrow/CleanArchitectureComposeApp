package com.example.domain.usecase

import com.example.domain.Interaction
import com.example.domain.repository.InteractionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UpdateInteractionUseCase(
    configuration: Configuration,
    private val interactionRepository: InteractionRepository
): UseCase<UpdateInteractionUseCase.Request, UpdateInteractionUseCase.Response>(configuration) {
    override fun process(request: Request): Flow<Response> {
        return interactionRepository.saveInteraction(request.interaction)
            .map {
                Response
            }
    }
    data class Request(val interaction: Interaction): UseCase.Request
    data object Response: UseCase.Response
}