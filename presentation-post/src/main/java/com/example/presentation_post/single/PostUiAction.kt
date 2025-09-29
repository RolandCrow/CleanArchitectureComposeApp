package com.example.presentation_post.single

import com.example.presentation_common.state.UiAction

sealed class PostUiAction: UiAction {
    data class Load(val postId: Long) : PostUiAction()
}