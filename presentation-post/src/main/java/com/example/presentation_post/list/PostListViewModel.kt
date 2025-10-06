package com.example.presentation_post.list

import androidx.lifecycle.viewModelScope
import com.example.domain.Interaction
import com.example.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import com.example.domain.usecase.UpdateInteractionUseCase
import com.example.presentation_common.UiState
import com.example.presentation_common.navigation.NavRoutes
import com.example.presentation_common.navigation.PostInput
import com.example.presentation_common.navigation.UserInput
import com.example.presentation_common.state.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsWithUsersWithInteractionUseCase,
    private val converter: PostListConverter,
    private val updateInteractionUseCase: UpdateInteractionUseCase
): MviViewModel<PostListModel, UiState<PostListModel>, PostListUiAction, PostListUiSingleEvent>() {
    override fun initState(): UiState<PostListModel> = UiState.Loading

    override fun handleAction(action: PostListUiAction) {
        when(action) {
            is PostListUiAction.Load -> {
                loadPosts()
            }
            is PostListUiAction.PostClick -> {
                updateInteraction(action.interaction)
                submitSingleEvent(
                    PostListUiSingleEvent.OpenPostScreen(
                        NavRoutes.Post.routeForPost(
                            PostInput(action.postId)
                        )
                    )
                )
            }
            is PostListUiAction.UserClick -> {
                updateInteraction(interaction = action.interaction)
                submitSingleEvent(
                    PostListUiSingleEvent.OpenUserScreen(
                        NavRoutes.User.routeForUser(
                            UserInput(action.userId)
                        )
                    )
                )
            }
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            useCase.execute(GetPostsWithUsersWithInteractionUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }

    private fun updateInteraction(interaction: Interaction) {
        viewModelScope.launch {
            updateInteractionUseCase.execute(
                UpdateInteractionUseCase.Request(
                    interaction.copy(
                        totalClicks = interaction.totalClicks+ 1
                    )
                )
            ).collect()
        }
    }
}