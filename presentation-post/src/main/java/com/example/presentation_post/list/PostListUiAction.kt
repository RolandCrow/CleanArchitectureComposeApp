package com.example.presentation_post.list

import com.example.domain.Interaction
import com.example.presentation_common.state.UiAction

sealed class PostListUiAction: UiAction {
    data object Load: PostListUiAction()
    data class UserClick(val userId: Long, val interaction: Interaction): PostListUiAction()
    data class PostClick(val postId: Long, val interaction: Interaction): PostListUiAction()
}