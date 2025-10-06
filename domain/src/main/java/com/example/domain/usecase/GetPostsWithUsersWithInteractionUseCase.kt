package com.example.domain.usecase

import com.example.domain.Interaction
import com.example.domain.entity.PostWithUser
import com.example.domain.repository.InteractionRepository
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetPostsWithUsersWithInteractionUseCase(
    configuration: Configuration,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val interactionRepository: InteractionRepository
): UseCase<GetPostsWithUsersWithInteractionUseCase.Request, GetPostsWithUsersWithInteractionUseCase.Response>(configuration) {
    override fun process(request: Request): Flow<Response> =
        combine(
            postRepository.getPosts(),
            userRepository.getUsers(),
            interactionRepository.getInteraction()
        ) { posts, users, interaction ->
            val postUsers = posts.map { post ->
                val user = users.first {
                    it.id == post.userId
                }
                PostWithUser(post,user)
            }
            Response(postUsers,interaction)
        }

    data object Request: UseCase.Request
    data class Response(
        val posts: List<PostWithUser>,
        val interactionId: Interaction
    ): UseCase.Response
}